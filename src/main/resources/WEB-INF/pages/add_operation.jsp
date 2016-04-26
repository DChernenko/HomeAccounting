<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form class="form-horizontal" role="form" action="/add_operations" method="post">
        <h1>Add operation</h1>
        <div class="form-group ">
            <label class="control-label col-sm-2 " for="sum">Sum: </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="sum" placeholder="Enter sum" name="sum" value="10">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2 " for="date">Date: </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="date" placeholder="Enter date" name="date"
                       value="22/12/2016" pattern="\d{1,2}/\d{1,2}/\d{4}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2 " for="date">Cate:</label>
            <div class="col-sm-10">
                <select class="selectpicker form-control " name="group">
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2 " for="date">Type of operation:</label>
            <div class="col-sm-10">
                <input type="radio" name="costs" value="true">Costs<br>
                <input type="radio" name="costs" value="false">Income<br>
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