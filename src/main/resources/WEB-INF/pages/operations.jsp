<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Titleperations</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="generic-container">
    <div class="panel panel-default">

        <div class="panel-heading"><span class="lead">List of Operations </span></div>
        <div class="panel-heading"><span class="lead">Balnce:${balance}</span></div>
        <div class="well">
            <a href="<c:url value="/add_operation" />">Add new operation</a>
        </div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Value</th>
                <th>Type</th>
                <th>Category</th>
                <th>Date</th>
                <th width="100"></th>
                <th width="100"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${operations}" var="operation">
                <tr>
                    <td>${operation.sum}</td>
                    <td><c:choose>
                        <c:when test="${operation.flagProfit}">
                            Profit
                        </c:when>
                        <c:otherwise>
                            Costs
                        </c:otherwise>
                    </c:choose>
                    </td>
                    <td>${operation.category.name}</td>
                    <td>${operation.date}</td>
                    <td><a href="<c:url value='/edit-operation-${operation.id}' />"
                           class="btn btn-success custom-width">edit</a></td>
                    <td><a href="<c:url value='/delete-operation-${operation.id}' />"
                           class="btn btn-danger custom-width">delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>