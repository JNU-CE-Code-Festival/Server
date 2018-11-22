var codeMirror;
window.onload = function () {
    var textarea = document.querySelector('#textbox');
    var readOnlyCodeMirror = CodeMirror(textarea, {
        theme: "darcula",
        lineNumbers: true,
        mode: {
            name: "text/x-csrc",
            json: false,
        },
    });  
    codeMirror = readOnlyCodeMirror;

    codeMirror.setValue(template.c);
};