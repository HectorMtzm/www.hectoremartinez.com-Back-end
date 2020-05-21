function getKey(e){
    let key_code=e.keyCode;
    switch(key_code){
        case 37: //left arrow key
            moveLeft();
            break;
        case 38: //Up arrow key
            moveUp();
            break;
        case 39: //right arrow key
            moveRight();
            break;
        case 40: //down arrow key
            moveDown();
            break;
        default:
            document.activeElement.value=e.key;
    }
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

function clearBoard() {
    for(let i = 0; i < 81; i++){
        document.getElementById(i).value="";
    }
}

function validate(){
    for(var i = 0; i <81; i++){
        if(/[^0-9]/.test(parseInt(document.getElementById(i).value))){
            document.getElementById(i).value="";
        }
    }
}

document.onkeydown = getKey;