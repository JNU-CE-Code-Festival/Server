var submitCode = document.querySelector('#submitCode');
var codeBlock = document.querySelector('#textbox');
var resultBlock = document.querySelector('div.result');
var langSel = document.querySelector('.lang');
var username = document.querySelector('p.username').innerText.trim();

function onLangChange() {
    let langValue = langSel.value;
    codeMirror.setValue(template[langValue]);
    switch(langValue) {
        case 'c':
            codeMirror.setOption('mode', 'text/x-csrc');
            break;
        case 'cpp':
            codeMirror.setOption('mode', 'text/x-c++src');
            break;
        case 'java':
            codeMirror.setOption('mode', 'text/x-java');
            break;
        case 'js':
            codeMirror.setOption('mode', 'text/javascript');
            break;
        case 'py':
            codeMirror.setOption('mode', 'text/x-python')
            break;
    }
}

submitCode.addEventListener('mousedown', function(event) {
    let code = codeMirror.getValue();
    console.log(code, "를 넘기자.")
    fetch(
        `/compile`, 
        {
            'method':'POST',
            'headers': {
                'Content-Type': 'application/json',
            },
            'body': JSON.stringify({
                'code': code,
                'createAuthor': username,
                'language' : langSel.value
            }),
        }
        )
        .then(data => data.text())
        .then(compile => resultBlock.innerText = compile);
});