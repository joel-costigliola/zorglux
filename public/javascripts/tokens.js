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
        //      /inominax/tokens/:tokenCollectionName/add/:token
        $.post('/inominax/tokens/' + encodeURIComponent(tokenCollection) + '/add/' + encodeURIComponent(newToken), function () {
            $('#tokenCollectionSelect').trigger('change');
        });
    }
}

$(document).ready(function () {

    $('#tokenCollectionSelect').change(function () {
        displayTokenCollection($('#tokenCollectionSelect option:selected').val());
    }).trigger('change');

    $('#newTokenCollection').click(function () {
        newTokenCollection();
    });

    $('#deleteTokenCollection').click(function () {
        deleteTokenCollection($('#tokenCollectionSelect option:selected').val());
    });

    $('#addToken').click(function () {
        addTokenToCollection($('#newToken').val(), $('#tokenCollectionSelect option:selected').val());
    });

});


