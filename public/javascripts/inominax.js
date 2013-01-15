
function displaySavedNamesCollection(savedNamesCollection) {
    $('#savedNamesCollection').load('/inominax/' + savedNamesCollection);
}

$(document).ready(function() {
    $('#selectedSavedNamesCollection').change(function () {
        displaySavedNamesCollection($('#selectedSavedNamesCollection option:selected').text());
    }).trigger('change');
});