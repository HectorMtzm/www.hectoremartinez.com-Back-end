document.onkeydown = function (e){
    const key_code=e.key;

    if(key_code == "ArrowLeft")
        moveLeft();
    else if(key_code == "ArrowUp")
        moveUp();
    else if(key_code == "ArrowRight")
        moveRight();
    else if(key_code == "ArrowDown")
        moveDown();
    else if(/[1-9]/.test(key_code)){
        updateBoardp1();
        document.activeElement.value = e.key;
        updateBoardp2();
    }
    else if(key_code == "Backspace") {
        updateBoardp1();
        document.activeElement.value = "";
    }
    else if(key_code == "Tab"){
    }
    else
        e.preventDefault();
}

function moveLeft(){
    let position = document.activeElement.id;
    position = parseInt(position);

    position -= 1;

    switch (position) {
        case -1: position=8; break;
        case 8: position=17; break;
        case 17: position=26; break;
        case 26: position=35; break;
        case 35: position=44; break;
        case 44: position=53; break;
        case 53: position=62; break;
        case 62: position=71; break;
        case 71: position=80; break;
    }

    document.getElementById(position.toString()).focus();
}
function moveUp(){
    let position = document.activeElement.id;
    position = parseInt(position);

    position -= 9;
    if (position < 0) {
        position = position + 81;
    }

    document.getElementById(position.toString()).focus();
}
function moveRight(){
    let position = document.activeElement.id;
    position = parseInt(position);

    position++;

    switch (position) {
        case 9: position=0; break;
        case 18: position=9; break;
        case 27: position=18; break;
        case 36: position=27; break;
        case 45: position=36; break;
        case 54: position=45; break;
        case 63: position=54; break;
        case 72: position=63; break;
        case 81: position=72; break;
    }

    document.getElementById(position.toString()).focus();
}
function moveDown(){
    let position = document.activeElement.id;
    position = parseInt(position);

    position += 9;
    if (position > 80) {
        position = position - 81;
    }

    document.getElementById(position.toString()).focus();
}
function updateBoardp1() {

    var itemsIdH = new Array();
    var itemsIdV = new Array();
    var itemsIdSquare = new Array();

    const elementId = document.activeElement.id;
    const elementValue = document.activeElement.value;
    const boxStart = findSquareStart(elementId);
    const elementMaxIdValueH = elementId - (elementId % 9) + 9;
    const elementMinIdValueV = minValue();
    function minValue() {
        let minValue = elementId;
        while(minValue > 8){
            minValue -= 9;
        }
        return minValue;
    }

    for (let i = elementMaxIdValueH - 9; i < elementMaxIdValueH; i++) {
        if (document.getElementById(i).value == elementValue) {
            itemsIdH.push(document.getElementById(i).id);
        }
    }

    for (let j = elementMinIdValueV; j < 81; j+=9) {
        if (document.getElementById(j).value == elementValue) {
            itemsIdV.push(document.getElementById(j).id);
        }
    }

    for (let z = boxStart; z < boxStart + 3; z++) {
        if (document.getElementById(z).value == elementValue) {
            itemsIdSquare.push(document.getElementById(z).id);
        }
    }
    for (let z = (boxStart + 9); z < boxStart + 12; z++) {
        if (document.getElementById(z).value == elementValue) {
            itemsIdSquare.push(document.getElementById(z).id);
        }
    }
    for (let z = (boxStart + 18); z < boxStart + 21; z++) {
        if (document.getElementById(z).value == elementValue) {
            itemsIdSquare.push(document.getElementById(z).id);
        }
    }

    if(elementValue != ""){
        if(itemsIdH.length == 2){
            itemsIdH.forEach(id => document.getElementById(id).style.color = null);
        }
        else {
            document.getElementById(elementId).style.color = null;
        }

        if(itemsIdV.length == 2){
            itemsIdV.forEach(id => document.getElementById(id).style.color = null);
        }
        else {
            document.getElementById(elementId).style.color = null;
        }

        if(itemsIdSquare.length == 2){
            itemsIdSquare.forEach(id => document.getElementById(id).style.color = null);
        }
        else {
            document.getElementById(elementId).style.color = null;
        }
    }
}

function updateBoardp2() {

    var itemsId = new Array();

    const elementId = document.activeElement.id;
    const elementValue = document.activeElement.value;
    const boxStart = findSquareStart(elementId);
    const elementMaxIdValueH = elementId - (elementId % 9) + 9;
    const elementMinIdValueV = minValue();
    function minValue() {
        let minValue = elementId;
        while(minValue > 8){
            minValue -= 9;
        }
        return minValue;
    }

    //checks if the horizontal line is valid with the new number
    for (let i = elementMaxIdValueH - 9; i < elementMaxIdValueH; i++) {
        if (document.getElementById(i).value == elementValue) {
            itemsId.push(document.getElementById(i).id);
        }
    }
    if(itemsId.length > 1){
        itemsId.forEach(id => document.getElementById(id).style.color = "red");
    }

    itemsId = new Array();

    //Checks vertical line is valid with the new number
    for (let j = elementMinIdValueV; j < 81; j+=9) {
        if (document.getElementById(j).value == elementValue) {
            itemsId.push(document.getElementById(j).id);
        }
    }
    if(itemsId.length > 1){
        itemsId.forEach(id => document.getElementById(id).style.color = "red");
    }

    itemsId = new Array();

    //Checks if the square section is valid with the new number
    for (let z = boxStart; z < boxStart + 3; z++) {
        if (document.getElementById(z).value == elementValue) {
            itemsId.push(document.getElementById(z).id);
        }
    }
    for (let z = (boxStart + 9); z < boxStart + 12; z++) {
        if (document.getElementById(z).value == elementValue) {
            itemsId.push(document.getElementById(z).id);
        }
    }
    for (let z = (boxStart + 18); z < boxStart + 21; z++) {
        if (document.getElementById(z).value == elementValue) {
            itemsId.push(document.getElementById(z).id);
        }
    }
    if(itemsId.length > 1){
        itemsId.forEach(id => document.getElementById(id).style.color = "red");
    }

}

function clearBoard() {
    for(let i = 0; i < 81; i++){
        document.getElementById(i).value="";
        document.getElementById(i).style.color = null;
    }
}

function findSquareStart(elementId) {
    let BoxLeftBorder = elementId - (elementId % 3);
    return BoxLeftBorder - ((Math.floor(BoxLeftBorder / 9) % 3) * 9);
}

function validate() {
    for (let i = 0; i < 81; i++) {
        if (/[^0-9]/.test(parseInt(document.getElementById(i).value))) {
            document.getElementById(i).value = "";
        }
    }
}