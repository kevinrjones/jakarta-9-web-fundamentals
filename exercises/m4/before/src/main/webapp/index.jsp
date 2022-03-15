<!DOCTYPE html>
<html>
<head>
    <title><%= application.getInitParameter("productName") %></title>
    <link href='bootstrap/css/bootstrap.min.css' rel="stylesheet" type="text/css"/>
</head>
<link href="app.css" rel="stylesheet" type="text/css"/>
<body>

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
                        <a class="nav-link" href='showlogin.do'>Login</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Color
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li>Default</li>
                            <li>Red</li>
                            <li>Blue</li>
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