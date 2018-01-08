<%-- 
    Document   : layout
    Created on : 13-Nov-2017, 13:22:18
    Author     : Hidjou
--%>

<%@tag description="page layout" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="layout"%>

<%-- any content can be specified here e.g.: --%>

<html>
    <head>
        <title>Admin panel</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/custom.css">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-inverse" role="navigation">
              <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                  </button>
                  <a class="navbar-brand" href="index.jsp">Antique shop</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
                  <ul class="nav navbar-nav">
                    <li><a href="AdminServlet?action=products">Products</a></li>
                    <li><a href="AdminServlet?action=orders">Orders</a></li>
                  </ul>
                </div><!-- /.navbar-collapse -->
              </div><!-- /.container -->
            </nav>
        </header>
        
        <div class="container">
            <jsp:doBody/>
        </div>

        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>