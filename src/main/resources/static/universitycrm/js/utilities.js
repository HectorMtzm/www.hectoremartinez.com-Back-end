function submitForm() {
    var form = document.getElementById("update-list");

    if(validateSearchByName()){
        form.submit();
    }

    return false;
}

function validateSearchByName() {
    var name = document.getElementById("search").value;

    if(name == "") {
        removeEmptyAttr();
        return true;
    }

    var names = name.split(" ");

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

// Removes empty attributes of the form
function removeEmptyAttr() {
    var order = document.getElementById("order");
    var paSi = document.getElementById("paSi");
    var search = document.getElementById("search");

    if(order.value == 1)
        order.name="";
    if(paSi.value == 15)
        paSi.name="";
    if(search.value == "")
        search.name="";
}