function displayTokenCollection(tokenCollection) {
    $('#tokenCollection').load('/inominax/tokens/' + encodeURIComponent(tokenCollection));
}

function newTokenCollection() {
    var newTokenCollectionName = window.prompt("Please enter the name of the token collection");
    if (!newTokenCollectionName) return;
    // TODO check that name is not already used, in that case just select it after notifying user of this existing collection name
    // for that returns an error for the post request
    $.post('/inominax/tokens/' + encodeURIComponent(newTokenCollectionName), function () {
        $('#tokenCollectionSelect').append('<option value="' + newTokenCollectionName + '" selected="selected">' + newTokenCollectionName + '</option>');
        // TODO : sort tokenCollectionSelect options
        $('#tokenCollectionSelect').trigger('change');
    });
}

function deleteTokenCollection(tokenCollection) {
    if (window.confirm(tokenCollection + " will be deleted.")) {
        $.post('/inominax/tokens/delete/' + encodeURIComponent(tokenCollection), function () {
            $("#tokenCollectionSelect option[value='" + tokenCollection + "']").remove();
            $('#tokenCollectionSelect').trigger('change');
        });
    }
}

function addTokenToCollection(newToken, tokenCollection) {
    if (newToken) {
        $.post('/inominax/tokens/' + encodeURIComponent(tokenCollection) + '/add/' + encodeURIComponent(newToken), function () {
            $('#tokenCollectionSelect').trigger('change');
        });
    }
}

function removeTokenFromCollection(token, tokenCollection) {
    // to play it safe, uncomment 'if'
    //if (window.confirm("Delete '" + token + "' from " + tokenCollection + " tokens ?")) {
    $.post('/inominax/tokens/' + encodeURIComponent(tokenCollection) + '/delete/' + encodeURIComponent(token), function () {
        $('#tokenCollectionSelect').trigger('change');
    });
    //}
}

function selectedTokenCollection() {
    return $('#tokenCollectionSelect option:selected').val();
}

$(document).ready(function () {

    $('#tokenCollectionSelect').change(function () {
        displayTokenCollection(selectedTokenCollection());
    }).trigger('change');

    $('#newTokenCollection').click(function () {
        newTokenCollection();
    });

    $('#deleteTokenCollection').click(function () {
        deleteTokenCollection(selectedTokenCollection());
    });

    $('#addToken').click(function () {
        addTokenToCollection($('#newToken').val(), selectedTokenCollection());
    });

    $("#tokenCollection").on('click', ".x", function () {
        var token = this.id.slice(0, -2);
        removeTokenFromCollection(token, selectedTokenCollection());
    });

});


