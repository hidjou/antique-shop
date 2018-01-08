<%-- 
    Document   : loogin
    Created on : 12-Dec-2017, 16:58:06
    Author     : Hidjou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Admin Login</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/custom.css">
    <link rel="stylesheet" href="css/login.css">
  </head>

  <body>

    <div class="container">
      <form class="form-signin" action="AdminServlet" method="POST">
          <center><h2 class="form-signin-heading">Log in</h2></center>
          <% if("true".equalsIgnoreCase((String)request.getAttribute("wrongPassword"))) { %>
          <p style="color: red;">Wrong credentials, try again</p>
          <% } %>
        <label for="inputEmail" class="sr-only">Username</label>
        <input type="text" name="user" id="inputEmail" class="form-control" placeholder="Username" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
      </form>

    </div> <!-- /container -->
  </body>
</html>
