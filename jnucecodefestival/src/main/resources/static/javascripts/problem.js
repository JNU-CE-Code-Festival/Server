var tabNavigator = document.querySelector('.tab_nav');
var container = document.querySelector('.wrap');

class Problem {
    constructor() {

        this.initTabNav = this.initTabNav.bind(this);
        init();
    }

    renderList() {

    }

    init() {
        tabNavigator.addEventListener('click', initializeTabNavigator.bind(this));
    }

    initializeTabNavigator(event) {
        console.log(event.target, event.currentTarget);
    }
}

new Problem();