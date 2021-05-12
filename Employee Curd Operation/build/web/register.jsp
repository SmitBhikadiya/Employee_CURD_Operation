<%-- 
    Document   : register
    Created on : 6 May, 2021, 10:10:59 AM
    Author     : Smit Bhikadiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/index-css.css">
    <title>Register</title>
    </head>
    <body>
        <div class="container login mt-2 p-3">
        <form action="registerServlet" method="POST">
            <div class="text-center text-dark logo mb-2">
                <h2>Register Page</h2>
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" placeholder="Username" name="uname">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" placeholder="Email" name="uemail">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" placeholder="Password" name="pwd">
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-success" value="Register">
                <a href="index.html" class="btn btn-dark">Login</a>
            </div>
        </form></div>
    </body>
</html>
