<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

<div class="container">
    <%--<h1>Registration form</h1>--%>
    <c:choose>
        <c:when test="${edit}">
            <h1>Update user</h1>
        </c:when>
        <c:otherwise>
            <h1>Registration user</h1>
        </c:otherwise>
    </c:choose>

    <form:form method="POST" modelAttribute="user" class="form-horizontal">
        <form:input type="hidden" path="id" id="id" class="form-control input-sm"/>
        <div class="row">
            <div class="form-group">
                <label class="control-label col-sm-2 " for="login">Login:</label>
                <div class="col-sm-10">
                    <form:input type="text" path="login" id="login" class="form-control input-sm"/>
                    <div class="has-error"><form:errors path="login" class="has-error help-block"/></div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2 " for="password">Password:</label>
                <div class="col-sm-10">
                    <form:input type="password" path="password" id="password" class="form-control input-sm"/>
                    <div class="has-error"><form:errors path="password" class="has-error help-block"/></div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2 " for="email">Email:</label>
                <div class="col-sm-10">
                    <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                    <div class="has-error"><form:errors path="email" class="has-error help-block"/></div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2 " for="fullName">Full name:</label>
                <div class="col-sm-10">
                    <form:input type="text" path="fullName" id="fullName" class="form-control input-sm"/>
                    <div class="has-error"><form:errors path="fullName" class="has-error help-block"/></div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2 " for="age">Age:</label>
                <div class="col-sm-10">
                    <form:input type="text" path="age" id="age" class="form-control input-sm"/>
                    <div class="has-error"><form:errors path="age" class="has-error help-block"/></div>
                </div>
            </div>

            <div class="form-group">
                <div class="form-actions right-container">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a
                                href="<c:url value="redirect:/authentication" />">Cancel</a>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a
                                href="<c:url value="redirect:/authentication" />">Cancel</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>