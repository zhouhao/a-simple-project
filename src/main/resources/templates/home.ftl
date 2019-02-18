<#include "layout/layout.ftl"/>
<#assign DateFormatUtils=statics['me.hzhou.todo.util.DateFormatUtils']>
<#assign inlineJs>
    <script src="/js/app.js"></script>
</#assign>
<@layout>
    <div class="jumbotron">
        <div class="col-sm-12 mx-auto">
            <h1>Incoming List</h1>
            <table class="table table-bordered table-striped datatable">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Content</th>
                    <th scope="col">User</th>
                    <th scope="col">Remind Time</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <#list todos as todo>
                    <tr>
                        <th>${todo.id}</th>
                        <td>${todo.content}</td>
                        <td>${todo.user.name}</td>
                        <td>${DateFormatUtils.format(todo.remindTime)}</td>
                        <td><a href="#" data-id="${todo.id}" class="todo_action" data-method="complete"
                               data-confirm="Are you sure to mark this as completed?">Complete</a> |
                            <a href="#" data-id="${todo.id}" class="todo_action" data-method="delay"
                               data-confirm="Are you sure to delay this 30 minutes?">Delay</a> |
                            <a href="#" data-id="${todo.id}" class="todo_action" data-method="sms"
                               data-confirm="Are you sure to send text message now?">SMS</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="jumbotron">
        <div class="col-sm-10 mx-auto">
            <h1>Add New Todo</h1>
            <form id="add_todo" method="post">
                <div class="form-group">
                    <label for="user_id">Target User</label>
                    <select class="form-control" id="user_id" name="user_id">
                        <#list users as user>
                            <option value="${user.id}">${user.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label for="content">Event</label>
                    <textarea class="form-control" id="content" name="content" placeholder="what to do"
                              required></textarea>
                </div>
                <div class="form-group">
                    <label for="remind_time">Reminder Time</label>
                    <input type="datetime-local" class="form-control" id="remind_time" name="remind_time" required>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</@layout>