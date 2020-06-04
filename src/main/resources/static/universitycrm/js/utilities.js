$('#id').keypress(function (e) {
    let key = e.keyCode;
    if(key == 13)
        $('#findId')[0].click();
});

function formatDocument(){
    formatPhoneNumber();
}

function formatPhoneNumber() {
    let phoneNumber = $('#phoneNumber').text();
    if(phoneNumber != ""){
        $('#phoneNumber').text("(" + phoneNumber.slice(0,3) + ") "
            + phoneNumber.slice(3,6) + "-" + phoneNumber.slice(6,10));
    }

}

function submitForm(type) {
    let form = document.getElementById("update-list");

    if(type == 's') {
        if (validateSearchByName())
            form.submit();
    }
    else if(type == 'i') {
        if (validateSearchByNumber())
            form.submit();
    }

    return false;
}

function validateSearchByName() {
    let name = document.getElementById("search").value;

    if(name == "") {
        removeEmptyAttr();
        return true;
    }

    let names = name.split(" ");

    if(names.length != 2){
        alert("Please enter the first and last name");
        return false;
    }
    else if(names.length == 2){
        names[0] = names[0].charAt(0).toUpperCase() + names[0].slice(1);
        names[1] = names[1].charAt(0).toUpperCase() + names[1].slice(1);

        document.getElementById("search").value = names.join(' ');
        removeEmptyAttr();
        return true;
    }
}

function validateSearchByNumber(){
    let number = document.getElementById("search").value;
    let intNumber = parseInt(number);

    if(number == ""){
        removeEmptyAttr();
        return true;
    }

    else if(number == intNumber) {
        if (number.toString().length == 4){
            removeEmptyAttr();
            return true;
        }
    }
    alert("Please enter a valid course number");
    return false;
}

// Removes empty attributes of the form
function removeEmptyAttr() {
    let order = document.getElementById("order");
    let paSi = document.getElementById("paSi");
    let search = document.getElementById("search");

    if(order.value == 1)
        order.name="";
    if(paSi.value == 15)
        paSi.name="";
    if(search.value == "")
        search.name="";
}

function assignId(){
    let id = document.getElementById("id").value;
    let href = document.getElementById("findId").getAttribute("href");

    if(id != parseInt(id) || id == "" || id < 1)
        return false;

    href += id;
    document.getElementById("findId").setAttribute("href", href);
    return true;
}

formatDocument();


