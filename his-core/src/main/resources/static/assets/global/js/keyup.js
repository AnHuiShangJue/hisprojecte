$(document).ready(function () {
    // var grid = new Datatable();
    $("#searchForm .row").find("input").keyup(function (event) {
        var length = $(this).length;
        if (length != 0) {
            event.preventDefault();
            if (event.keyCode === 13) {
                grid.search();
            }
        }
    });
});