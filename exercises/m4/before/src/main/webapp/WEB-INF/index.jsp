<!DOCTYPE html>
<html>
<head>
    <title><%= application.getInitParameter("productName") %></title>
    <link href='bootstrap/css/bootstrap.min.css' rel="stylesheet" type="text/css"/>
</head>
<link href="app.css" rel="stylesheet" type="text/css"/>
<body>

<%@include file="_header.jsp"%>

<section class="container px-4">
    <div class="row row-cols-2 gy-5">
        <div class="col-4 p-4">
            <div class="p-3 border bg-light">

            </div>
        </div>
        <div class="col-8 p-4">

        </div>
    </div>
</section>
<script src="bootstrap/js/bootstrap.js"></script>
</body>
</html>