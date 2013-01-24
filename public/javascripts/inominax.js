function displayNameCollection(nameCollection) {
    $('#nameCollection').load('/inominax/' + encodeURIComponent(nameCollection));
}

function newNameCollection() {
    var name = window.prompt("Please enter the name of the collection", " names");
    $.post('/inominax/' + encodeURIComponent(name), function () {
        $('#nameCollectionSelect').append('<option value="' + name + '" selected="selected">' + name + '</option>');
        // TODO : sort nameCollectionSelect options
        $('#nameCollectionSelect').trigger('change');
    });
}

$(document).ready(function () {

    $('#nameCollectionSelect').change(function () {
        displayNameCollection($('#nameCollectionSelect option:selected').text());
    }).trigger('change');

    $('#newNameCollection').click(function () {
        newNameCollection();
    });

    $("#generateNamesForm").submit(function (event) {
        // stop form from submitting normally
        event.preventDefault();
        // post serialized generateNamesForm form and display result in #generatedNames div
        $.post('/inominax', $(this).serialize()).done(function (data) {
            $("#generatedNames").empty().append(data);
        });
    });
});


