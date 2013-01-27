function displayTokenCollection(tokenCollection) {
    $('#tokenCollection').load('/inominax/tokens/' + encodeURIComponent(tokenCollection));
}

function newTokenCollection() {
    var name = window.prompt("Please enter the name of the token collection");
    if (!name) return;
    // TODO check that name is not already used, in that case just select it after notifying user of this existing collection name
    // for that returns an error for the post request
    $.post('/inominax/tokens/' + encodeURIComponent(name), function () {
        $('#tokenCollectionSelect').append('<option value="' + name + '" selected="selected">' + name + '</option>');
        // TODO : sort tokenCollectionSelect options
        $('#tokenCollectionSelect').trigger('change');
    });
}

$(document).ready(function () {

    $('#tokenCollectionSelect').change(function () {
        displayTokenCollection($('#tokenCollectionSelect option:selected').text());
    }).trigger('change');

    $('#newTokenCollection').click(function () {
        newTokenCollection();
    });

});


