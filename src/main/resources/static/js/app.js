$(function () {
    $("#add_todo").submit(function (event) {
        let data = {};
        $(this).serializeArray().map(function (x) {
            data[x.name] = x.value;
        });

        request('/todo', 'POST', data, function () {
            toastr.success("Todo is added successfully");
        }, function () {
            toastr.error("failed to add todo");
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
                request(`/todo/${todoId}/${method}`, 'POST', {},
                    function () {
                        toastr.success("Success");
                    },
                    function () {
                        toastr.error("Failure");
                    });
            }
        }
    });

    $("#add_user").submit(function (event) {
        let data = {};
        $(this).serializeArray().map(function (x) {
            data[x.name] = x.value;
        });
        let self = this;
        request('/user', 'POST', data, function () {
            toastr.success("New user is added successfully");
            $(self).trigger('reset');
        }, function () {
            toastr.error("failed to add new User");
        });
        event.preventDefault();
    });

    $('.user-update-action').on('click', function () {
        const tr = $(this).closest('tr');
        $('#user-id').val(tr.find('.id').html());
        $('#name').val(tr.find('.name').html());
        $('#phone').val(tr.find('.phone').html());
        $('#user-update-modal').modal('show')
    });

    $("#update_user").submit(function (event) {
        let data = {};
        $(this).serializeArray().map(function (x) {
            data[x.name] = x.value;
        });
        let self = this;
        request('/user', 'PUT', data, function () {
            toastr.success("User is updated successfully");
            $(self).trigger('reset');
            $('#user-update-modal').modal('hide');
        }, function () {
            toastr.error("failed to update User Info");
        });
        event.preventDefault();
    });

    /*************** Utils Method below ***************/

    function request(url, method, data, success, error) {
        $.ajax({
            'url': url,
            'method': method,
            'dataType': 'json',
            'contentType': 'application/json',
            'data': JSON.stringify(data),
            'success': success,
            'error': error
        })
    }
});