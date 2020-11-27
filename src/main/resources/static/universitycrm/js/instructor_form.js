$("#firstName").on('input', () => {
    $("#firstName").removeClass("error-border").next().remove();
});

$("#lastName").on('input', () => {
    $("#lastName").removeClass("error-border").next().remove();
});

$("#email").on('input', () => {
    $("#email").removeClass("error-border").next().remove();
});

$("#details\\.phoneNumber").on('input', () => {
    $("#details\\.phoneNumber").removeClass("error-border").next().remove();
});

$("#details\\.major").on('input', () => {
    $("#details\\.major").removeClass("error-border").next().remove();
});

$("#details\\.dateOfBirth").on('input', () => {
    $("#details\\.dateOfBirth").removeClass("error-border").next().remove();
});

$("#details\\.address").on('input', () => {
    $("#details\\.address").removeClass("error-border").next().remove();
});

$("#details\\.city").on('input', () => {
    $("#details\\.city").removeClass("error-border").next().remove();
});

$("#details\\.state").on('input', () => {
    $("#details\\.state").removeClass("error-border").next().remove();
});

$("#details\\.office").on('input', () => {
    $("#details\\.office").removeClass("error-border").next().remove();
});
