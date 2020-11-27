window.onbeforeunload = function() {
    $("#navi-toggle").prop("checked", false);
}

$("form").submit(function(e) {
    if (grecaptcha.getResponse() === '') {
        e.preventDefault();
        alert("Please verify that you are not a robot.");
        return false;
    }
});