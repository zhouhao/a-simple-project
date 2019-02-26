<#include "layout/layout.ftl"/>
<#assign DateFormatUtils=statics['me.hzhou.todo.util.DateFormatUtils']>
<#assign inlineJs>
    <script src="/js/app.js"></script>
</#assign>
<@layout>
    <div class="jumbotron">
        <div class="col-sm-12 mx-auto">
            <h1>System Users</h1>
            <table class="table table-bordered table-striped datatable">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Phone</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <#list users as user>
                    <tr>
                        <th class="id">${user.id}</th>
                        <td class="name">${user.name}</td>
                        <td class="phone">${user.phone}</td>
                        <td><a href="#" data-id="${user.id}" class="user-update-action">Update</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="jumbotron">
        <div class="col-sm-10 mx-auto">
            <h1>Add New User</h1>
            <form id="add_user" method="post">

                <div class="form-group">
                    <label for="username">Username</label>
                    <input class="form-control" name="name" placeholder="user name" required>
                </div>

                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input class="form-control" name="phone" placeholder="phone, e.g. +16667778888"
                           pattern="\+[0-9]{11}"
                           required>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</@layout>

<!-- Modal -->
<div class="modal fade" id="user-update-modal" tabindex="-1" role="dialog" aria-labelledby="user-update-modal-title"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="user-update-modal-title">Update User Info</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form id="update_user" method="post">
                <input type="hidden" name="id" id="user-id" value="0">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input class="form-control" id="name" name="name" placeholder="user name" required>
                    </div>

                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input class="form-control" id="phone" name="phone" placeholder="phone, e.g. +16667778888"
                               pattern="\+[0-9]{11}"
                               required>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>