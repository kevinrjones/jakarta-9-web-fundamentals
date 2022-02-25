<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header class="clearfix">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <c:if test="${empty sessionScope.user}">
                            <a class="nav-link" href='<c:url value="/showlogin.do"/>'>Login</a>
                        </c:if>
                        <c:if test="${!empty sessionScope.user}">
                            <a class="nav-link" href='<c:url value="/logout.do"/>'>Logout ${user.name}</a>
                        </c:if>
                    </li>
                    <c:if test="${empty sessionScope.user == false}">
                        <li class="nav-item">
                            <a class="nav-link" href='<c:url value="/add.do"/>'>Create News Item</a>
                        </li>
                    </c:if>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Color
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href='<c:url value="/color.do?color=white"/>'>Default</a></li>
                            <li><a class="dropdown-item" href='<c:url value="/color.do?color=red"/>'>Red</a></li>
                            <li><a class="dropdown-item" href='<c:url value="/color.do?color=blue"/>'>Blue</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>