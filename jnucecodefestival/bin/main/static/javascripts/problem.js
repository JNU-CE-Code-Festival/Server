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
            "score": this.renderScore,
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
}

new Problem(tabNavigator, container, activeTab);