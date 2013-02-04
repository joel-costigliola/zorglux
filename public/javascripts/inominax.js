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


