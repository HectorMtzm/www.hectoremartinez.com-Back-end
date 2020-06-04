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

function displayForm(form){
    if(form == 'r'){
        document.getElementById("addReview").style.display = "block";
    }
    else if (form = 's'){
        document.getElementById("addStudent").style.display = "block";
    }
}

function hideForm(form){
    if(form == 'r'){
        document.getElementById("addReview").style.display = "none";
    }
    else if (form = 's'){
        document.getElementById("addStudent").style.display = "none";
    }
}

function validate(type) {
    if(type == 's'){
        const input = document.getElementById("studentIdInput").value;
        if(input == "" || input < 1){
            return false
        }
    }
    else if(type == 'r'){
        const input = document.getElementById("reviewInput").value;
        if(input == ""){
            return false
        }
    }

    return true;
}