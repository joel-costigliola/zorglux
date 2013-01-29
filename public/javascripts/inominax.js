function displayNameCollection(nameCollection) {
    $('#nameCollection').load('/inominax/' + encodeURIComponent(nameCollection));
}

function newNameCollection() {
    var name = window.prompt("Please enter the name of the collection", " names");
    if (!name) return;
    // TODO check that name is not already used, in that case just select it after notifying user of this existing collection name
    $.post('/inominax/' + encodeURIComponent(name), function () {
        $('#nameCollectionSelect').append('<option value="' + name + '" selected="selected">' + name + '</option>');
        // TODO : sort nameCollectionSelect options
        $('#nameCollectionSelect').trigger('change');
    });
}

function selectedNameCollection() {
    return $('#nameCollectionSelect option:selected').val();
}

function saveNameToCollection(name, nameCollection) {
    $.post('/inominax/names/' + encodeURIComponent(nameCollection) + '/add/' + encodeURIComponent(name), function () {
        // reload selected nameCollection
        $('#nameCollectionSelect').trigger('change');
    });
}

$(document).ready(function () {

    $('#nameCollectionSelect').change(function () {
        displayNameCollection(selectedNameCollection());
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

    $("#generatedNames").on('click', ".generatedName", function () {
        var generatedName = this.textContent;
        saveNameToCollection(generatedName, selectedNameCollection());
        $(this).remove();
    });

});


