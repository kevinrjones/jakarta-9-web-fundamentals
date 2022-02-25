<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>${initParam["productName"]}</title>
    <link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css"/>
</head>
<link href="<c:url value='/app.css'/>" rel="stylesheet" type="text/css"/>
<body>

<%@include file="_header.jsp"%>

<section class="container px-4">
    <div class="row row-cols-2 gy-5">
        <div class="col-4 p-4">
            <div class="p-3 border bg-light">
                <c:import url="_topics.jsp" var="topics"/>
                ${topics}
            </div>
        </div>
        <div class="col-8 p-4">
            <%@include file="_newsitems.jsp"%>
        </div>
    </div>
</section>
<script src="<c:url value='/bootstrap/js/bootstrap.js'/>"></script>
</body>
</html>