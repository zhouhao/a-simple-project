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
                        <th>${user.id}</th>
                        <td>${user.name}</td>
                        <td>${user.phone}</td>
                        <td><a href="#" data-id="${user.id}" class="user_update_action">Update</a>
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
                    <input class="form-control" id="name" name="name" placeholder="user name" required>
                </div>

                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input class="form-control" id="phone" name="phone" placeholder="phone, e.g. +15087778888"
                           pattern="\+[0-9]{11}"
                           required>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</@layout>