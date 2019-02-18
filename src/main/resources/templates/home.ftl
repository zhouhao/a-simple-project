<#include "layout/layout.ftl"/>
<#assign inlineJs>
    <script src="/js/app.js"></script>
</#assign>
<@layout>
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