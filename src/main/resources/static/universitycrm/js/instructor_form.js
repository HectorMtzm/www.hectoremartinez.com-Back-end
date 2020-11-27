$("#firstName").on('input', () => {
    $("#firstName").removeClass("error-border").next().remove();
});

$("#lastName").on('input', () => {
    $("#lastName").removeClass("error-border").next().remove();
});

$("#email").on('input', () => {
    $("#email").removeClass("error-border").next().remove();
});

$("#details\\.office").on('input', () => {
    $("#details\\.office").removeClass("error-border").next().remove();
});

$("#details\\.phoneNumber").on('input', () => {
    $("#details\\.phoneNumber").removeClass("error-border").next().remove();
});
