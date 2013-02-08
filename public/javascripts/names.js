function displayNameCollection(nameCollection) {
    $('#nameCollection').load('/inominax/names/' + encodeURIComponent(nameCollection));
}

function newNameCollection() {
    var newNameCollectionName = window.prompt("Please enter the name of the name collection");
    if (!newNameCollectionName) return;
    // TODO check that name is not already used, in that case just select it after notifying user of this existing collection name
    // for that returns an error for the post request
    $.post('/inominax/names/' + encodeURIComponent(newNameCollectionName), function () {
        var nameCollectionSelect = $('#nameCollectionSelect');
        nameCollectionSelect.append('<option value="' + newNameCollectionName + '" selected="selected">' + newNameCollectionName + '</option>');
        sortSelect("#nameCollectionSelect");
        nameCollectionSelect.trigger('change');
    });
}

function deleteNameCollection(nameCollection) {
    if (window.confirm(nameCollection + " will be deleted.")) {
        $.post('/inominax/names/delete/' + encodeURIComponent(nameCollection), function () {
            var nameCollectionSelect = $("#nameCollectionSelect");
            nameCollectionSelect.find("option[value='" + nameCollection + "']").remove();
            nameCollectionSelect.trigger('change');
        });
    }
}

function renameNameCollection(nameCollection) {
    var newNameCollectionName = window.prompt("Please enter the new name of " + nameCollection);
    if (!newNameCollectionName) return;
    // TODO check that name is not already used, in that case just select it after notifying user of this existing collection name
    // for that returns an error for the post request
    $.post('/inominax/names/' + encodeURIComponent(nameCollection) + '/rename/' + encodeURIComponent(newNameCollectionName), function () {
        var nameCollectionSelect = $('#nameCollectionSelect');
        var renamedOption = nameCollectionSelect.find("option[value='" + nameCollection + "']");
        renamedOption.val(newNameCollectionName);
        renamedOption.text(newNameCollectionName);
        sortSelect("#nameCollectionSelect");
        renamedOption.attr('selected', 'selected');
    });
}

function addNameToCollection(newName, nameCollection) {
    if (newName) {
        $.post('/inominax/names/' + encodeURIComponent(nameCollection) + '/add/' + encodeURIComponent(newName), function () {
            $('#nameCollectionSelect').trigger('change');
        });
    }
}

function removeNameFromCollection(name, nameCollection) {
    $.post('/inominax/names/' + encodeURIComponent(nameCollection) + '/delete/' + encodeURIComponent(name), function () {
        $('#nameCollectionSelect').trigger('change');
    });
}
function selectedNameCollection() {
    return $('#nameCollectionSelect').find('option:selected').val();
}

$(document).ready(function () {

    $('#nameCollectionSelect').change(function () {
        displayNameCollection(selectedNameCollection());
    }).trigger('change');

    $('#newNameCollection').click(function () {
        newNameCollection();
    });

    $('#deleteNameCollection').click(function () {
        deleteNameCollection(selectedNameCollection());
    });

    $('#renameNameCollection').click(function () {
        renameNameCollection(selectedNameCollection());
    });

    $('#addName').click(function () {
        addNameToCollection($('#newName').val(), selectedNameCollection());
    });

    $('#newName').keyup(function (event) {
        if (keyCode(event) == 13) { //Enter keycode
            addNameToCollection($('#newName').val(), selectedNameCollection());
        }
    });

    $("#nameCollection").on('click', ".deleteName", function () {
        var name = this.id.slice(0, -2);
        removeNameFromCollection(name, selectedNameCollection());
    });

    $("#generatedNames").on('click', ".name", function () {
        var generatedName = this.textContent;
        addNameToCollection(generatedName, selectedNameCollection());
        $(this).remove();
    });

});


