const tabs = document.querySelectorAll('[data-tab-target]')
const tabContent = document.querySelectorAll('[data-tab-content]')

tabs.forEach(tab => {
    tab.addEventListener('click', () => {
        const target = document.querySelector(tab.dataset.tabTarget)
        tabContent.forEach(tabContent =>{
            tabContent.classList.remove('active')
        })
        tabs.forEach(tab =>{
            tab.classList.remove('current')
        })
        tab.classList.add('current')
        target.classList.add('active')
    })
})
