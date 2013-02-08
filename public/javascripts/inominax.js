function keyCode(event) {
    return (event.keyCode ? event.keyCode : event.which);
}

function sortSelect(selectId) {
    $(selectId).html($(selectId + " option").sort(function (option1, option2) {
        return option1.text < option2.text ? -1 : 1
    }))
}

$(document).ready(function () {

    $("#generateNamesForm").submit(function (event) {
        // stop form from submitting normally
        event.preventDefault();
        // post serialized generateNamesForm form and display result in #generatedNames div
        $.post('/inominax', $(this).serialize()).done(function (data) {
            $("#generatedNames").empty().append(data);
        });
    });

});


