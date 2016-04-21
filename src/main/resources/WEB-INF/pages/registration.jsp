<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container" <%--style="width: 600px;"--%>>
    <h1>Registration form</h1>
    <form class="form-horizontal" role="form" action="/add_user" method="post" <%--style="width: 400px;"--%>>
        <div class="form-group ">
            <label class="control-label col-sm-2 " for="login">Login:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="login" placeholder="Enter login" name="login" value="login">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2 " for="pwd">Password:</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password"
                       value="login">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email:</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" placeholder="Enter email" name="email"
                       value="login@gmail.com">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" placeholder="Enter full name" name="full_name"
                       value="Petrych Petrych">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Age:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="age" placeholder="Enter age" name="age" value="18">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-lg btn-primary btn-block">Submit</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>