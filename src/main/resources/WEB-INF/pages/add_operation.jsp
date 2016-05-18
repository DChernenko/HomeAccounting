<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
    <c:choose>
        <c:when test="${edit}">
            <h1>Update operation</h1>
        </c:when>
        <c:otherwise>
            <h1>Add operation</h1>
        </c:otherwise>
    </c:choose>
    <form:form method="POST" modelAttribute="operation" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>
        <div class="row">
            <div class="form-group">
                <label class="control-label col-sm-2 " for="sum">Sum:</label>
                <div class="col-sm-10">
                    <form:input type="text" path="sum" id="sum" class="form-control input-sm"/>
                    <div class="has-error"><form:errors path="sum" class="has-error help-block"/></div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2 " for="date">Date:</label>
                <div class="col-sm-10">
                    <form:input type="date" path="date" id="date" class="form-control input-sm"/>
                    <div class="has-error"><form:errors path="date" class="has-error help-block"/></div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2 " for="date">Category:</label>
                <div class="col-sm-10">
                        <%--<form:select path="category"
                                     class="selectpicker form-control"
                                     id="category" items="${categories}"
                                     itemLabel="name" itemValue="id">

                        </form:select>--%>
                        <%--             <form:select path="category">
                                         <c:forEach items="${categories}" var="category">
                                             <form:option value="category">${category.name}</form:option>
                                         </c:forEach>
                                     </form:select>--%>
                        <%--    <form:select path="category">
                                <form:options items="${categories}" itemValue="id" itemLabel="name"/>
                            </form:select>--%>
                    <form:select path="category"
                                 class="selectpicker form-control"
                                 items="${categories}"
                                 itemLabel="name" itemValue="id"/>


                        <%--  <form:select path="category" class="selectpicker form-control" id="category">
                        <c:forEach items="${categories}" var="category">
                            <form:option
                                    value="${category.id}">${category.name}</form:option> &lt;%&ndash; <option value="${category.id}">${category.name}</option>&ndash;%&gt;
                        </c:forEach>
                    </form:select>--%>
                        <%--
                                            <form:select class="form-control" path="site">
                                            <form:option value="-1">Select...</form:option>
                                            <form:options items="${categoryList}" itemValue="categoryID" itemLabel="categoryName"/>
                                            </form:select>--%>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2 " for="date">Type of operation:</label>
                <div class="col-sm-10">
                    <form:radiobutton path="flagProfit" value="true" id="flagProfit"/> Profit <br>
                    <form:radiobutton path="flagProfit" value="false" id="flagProfit"/> Costs
                </div>
            </div>
            <div class="form-group">
                <div class="form-actions right-container">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a
                                href="<c:url value="/operations" />">Cancel</a>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Add" class="btn btn-primary btn-sm"/> or <a
                                href="<c:url value="/operations" />">Cancel</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>