$(function () {
    $("#add_todo").submit(function (event) {
        alert("Handler for .submit() called.");
        let data = {};
        $(this).serializeArray().map(function (x) {
            data[x.name] = x.value;
        });
        console.log(JSON.stringify(data));
        $.ajax({
            'url': '/todo',
            'method': 'POST',
            'dataType': 'json',
            'contentType': 'application/json',
            'data': JSON.stringify(data),
            'success': function () {
                toastr.success("Todo is added successfully");
            },
            'error': function () {
                toastr.error("failed to add todo");
            }

        });
        event.preventDefault();
        $(this).trigger('reset');
    });
});