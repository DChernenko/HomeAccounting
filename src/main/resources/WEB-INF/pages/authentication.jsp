<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="width: 300px;">
    <form action="/authentication_action" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="form-control pagination" name="username" placeholder="Email address" required
               autofocus
               value="devcolibri">
        <input type="password" class="form-control pagination" name="password" placeholder="Password" required
               value="1234">
        <button class="btn btn-lg btn-primary btn-block pagination" type="submit">Войти</button>
    </form>
    <a href="/registration">registration</a><br>
    <a href="/add_category">add category </a><br>
    <a href="/add_operation">operation</a><br>
</div>

</body>
</html>