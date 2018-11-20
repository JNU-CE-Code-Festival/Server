var tabNavigator = document.querySelector('ul.tab_nav');
var container = document.querySelector('.wrap');

class Problem {
    constructor() {

        this.init = this.init.bind(this);
        this.init();
    }

    renderList() {

    }

    init() {
        tabNavigator.addEventListener('click', this.initializeTabNavigator.bind(this));
    }

    initializeTabNavigator(event) {
        console.log(event.target, event.currentTarget);
    }
}

new Problem();