<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Category</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <c:choose>
        <c:when test="${edit}">
            <h1>Update category</h1>
        </c:when>
        <c:otherwise>
            <h1>Add category</h1>
        </c:otherwise>
    </c:choose>
    <form:form method="POST" modelAttribute="category" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>
        <div class="row">
            <div class="form-group">
                <label class="control-label col-sm-2 " for="name">Category name</label>
                <div class="col-sm-10">
                    <form:input type="text" path="name" id="name" class="form-control input-sm"/>
                    <div class="has-error"><form:errors path="name" class="has-error help-block"/></div>
                </div>
            </div>
            <div class="form-group">
                <div class="form-actions right-container">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a
                                href="<c:url value="/categories" />">Cancel</a>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Add" class="btn btn-primary btn-sm"/> or <a
                                href="<c:url value="/categories" />">Cancel</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </form:form>
</div>
<div class="well"><a href="<c:url value="/categories" />">Show categories</a></div>
</body>
</html>