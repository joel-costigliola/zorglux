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
});