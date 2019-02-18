$(function () {
    $("#add_todo").submit(function (event) {
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

    $('.todo_action').on('click', function () {
        let result = confirm($(this).data('confirm'));
        if (result) {
            let method = $(this).data('method');
            let todoId = $(this).data('id');
            if (method && todoId) {
                postReq(todoId, method);
            }
        }
    });


    function postReq(todoId, method) {
        $.ajax({
            'url': `/todo/${todoId}/${method}`,
            'method': 'POST',
            'dataType': 'json',
            'contentType': 'application/json',
            'success': function () {
                toastr.success("Success");
            },
            'error': function () {
                toastr.error("Failure");
            }

        });
    }
});