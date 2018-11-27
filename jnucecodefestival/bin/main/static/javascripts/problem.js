'use strict';

var tabNavigator = document.querySelector('ul.tab_nav');
var container = document.querySelector('.wrap');
var activeTab = document.querySelector('li.active');

class Problem {
    constructor(tabNavigator, container, activeTab) {
        this.tabNavigator = tabNavigator;
        this.container = container;
        this.activeTab = activeTab;

        this.bindFunction = this.bindFunction.bind(this);
        this.bindFunction();

        this.init();
        this.renderContainer(this.activeTab.id);
    }

    bindFunction() {
        this.init = this.init.bind(this);
        this.initTabNav = this.initTabNav.bind(this);
        this.onClickTab = this.onClickTab.bind(this);
        this.renderContainer = this.renderContainer.bind(this);
        this.renderProblem = this.renderProblem.bind(this);
        this.renderScore = this.renderScore.bind(this);
        this.renderRank = this.renderRank.bind(this);
    }
    renderList() {

    }

    init() {
        this.initTabNav();
    }

    initTabNav() {
        let tabs = document.querySelectorAll('li')
        tabs.forEach((item, index) => {
            item.addEventListener('click', this.onClickTab.bind(this));
        })
    }

    onClickTab(event) {
        let clickedTarget = event.currentTarget;
        if(clickedTarget !== this.activeTab) {
            this.activeTab.classList.remove('active');
            clickedTarget.classList.add('active');
            this.activeTab = clickedTarget;

            this.renderContainer(this.activeTab.id);
        }

        
    }

    renderContainer(id) {
        const switchState = {
            "score": this.renderRank,
            "problem": this.renderProblem,
        }[id];
        this.container.innerHTML = "";
        switchState();
    }

    renderScore() {
        console.log("score");
        try {
            this.container.innerHTML = "살려줘";
        } catch(err) {
            console.error(err);
        }
    }

    async renderProblem() {
        console.log("problem");
        try {
            let res = await fetch('/problemList', {method:'GET'})
            let json = await res.json();
            json.data.map(item => {
                let block = document.createElement('a');
                block.href = `/code?number=${item.Id}`;
                block.className = 'item';
                block.innerHTML = `
                    <div class="item_number">${item.problemNum}</div>
                    <div class="item_subject">${item.problemTitle}</div>
                `;
                console.log(block);
                this.container.appendChild(block);
            })
        } catch (err) {
            console.error(err);
        }
        
    }

    async renderRank() {
        console.log("rank");
        try {
            let res = await fetch('/rank', {method: 'GET'});
            let json = await res.json();
            console.log(json.data);
            console.log(json.rank);
            if(json.rank.length !== 0) {
                json.rank.map((rankItem, index) => {
                    let block = document.createElement('div');
                    block.className = 'item';

                    let itemNumber = document.createElement('div');
                    itemNumber.className  = "item_number";
                    itemNumber.innerText = index + 1;
                    block.appendChild(itemNumber);
                    let title = document.createElement('div');
                    title.className = 'item_subject';
                    title.innerText = rankItem.username;

                    block.appendChild(title);

                    let scoreBar = document.createElement('div');
                    scoreBar.className = 'score_bar';
                    
                    let userScoreHistory = json.data.filter(({ userName }) => userName === rankItem.username);

                    for(let i = 1; i <= 3; i++) {

                        let scoreBlock = document.createElement('div');
                        let isExists = 
                            userScoreHistory
                                .filter(({ problemNum }) => parseInt(problemNum) === i);
                                // .filter(({ score }) => parseInt(score) === 1);
                        console.log(isExists);
                        scoreBlock.className = (isExists.length !== 0 && isExists[0].score) ? "success" : "fail";
                        scoreBlock.innerHTML =`
                            <div class="hover-content">
                                ${isExists.length !== 0 ? isExists[0].submitCount : "미시도"}
                            </div>
                        `
                        scoreBar.appendChild(scoreBlock);
                    }
                    // json.data.filter(({userName}) => userName === rankItem.username).map(item => {
                    //     let scoreBlock = document.createElement('div');
                    //     block.innerText=item.userName;
                    //     block.innerHTML+='ba';
                    // })
                    block.appendChild(scoreBar);
                    this.container.appendChild(block);
                })
            }
        } catch (err) {
            console.error(err);
        }
    }
}

new Problem(tabNavigator, container, activeTab);