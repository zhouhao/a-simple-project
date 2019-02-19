<#macro layout>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Jekyll v3.8.5">
        <title>天下没有老婆大人会错过的事</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="//stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
              integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
              crossorigin="anonymous">
        <!-- Custom styles for this template -->
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.18/datatables.min.css"/>
        <link href="/css/navbar.css" rel="stylesheet">
        ${inlineCss!""}
    </head>
    <body>
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
            <a class="navbar-brand" href="#">咸蛋</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample09"
                    aria-controls="navbarsExample09" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExample09">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">一堆 <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">没有</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">用的</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="dropdown09" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">链接</a>
                        <div class="dropdown-menu" aria-labelledby="dropdown09">
                            <a class="dropdown-item" href="#">只是</a>
                            <a class="dropdown-item" href="#">为了</a>
                            <a class="dropdown-item" href="#">页面好看</a>
                        </div>
                    </li>
                </ul>
                <form class="form-inline my-2 my-md-0">
                    <input class="form-control" type="text" placeholder="Not Available" aria-label="Search">
                </form>
            </div>
        </nav>

        <main role="main">
            <#nested>
        </main>
    </div>
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
            integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
            crossorigin="anonymous"></script>
    <script src="//stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
            integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
            crossorigin="anonymous"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>
    <script type="text/javascript" src="//cdn.datatables.net/v/bs4/dt-1.10.18/datatables.min.js"></script>
    <script>
        $(function () {
            $('.datatable').DataTable({
                responsive: true
            });
        });
    </script>
    ${inlineJs!""}
    </body>
    </html>
</#macro>