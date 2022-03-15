<!DOCTYPE html>
<html>
<head>
    <title>${initParam["productName"]}</title>
    <link href='${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css' rel="stylesheet" type="text/css"/>
</head>
<link href="${pageContext.request.contextPath}/app.css" rel="stylesheet" type="text/css"/>
<body>

<%@include file="_header.jsp"%>

<section class="container px-4">
    <div class="row row-cols-2 gy-5">
        <div class="col-4 p-4">
            <div class="p-3 border bg-light">
                <%@include file="_topics.jsp"%>
            </div>
        </div>
        <div class="col-8 p-4">
            <%@include file="_newsitems.jsp"%>
        </div>
    </div>
</section>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
</body>
</html>