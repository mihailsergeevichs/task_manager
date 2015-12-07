<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title><spring:message code="label.tasks.title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/social-buttons-3.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-theme.css" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
<div class="page-header">
    <h1><spring:message code="label.homepage.title"/> <sec:authentication property="principal.firstName"/> <sec:authentication property="principal.lastName"/></h1>
</div>
<div class="container">
    <form action="/tasks/add" method="get">
        <input type="submit" value=<spring:message code="label.tasks.addTask"/>
               name="add" id="add" />
    </form>
</div>
<div class="container">
        <c:choose>
            <c:when test="${unchecked.size() == 0}">
                <p> <h1 class="alert-info"> <spring:message code="label.tasks.emptyList"/></h1> </p>
            </c:when>
            <c:otherwise>
                <h1 class="active"><spring:message code="label.tasks.unchecked"/></h1>
                <table class="table table-bordered">
                    <tbody>
                    <tr>
                        <th><spring:message code="label.tasks.label"/></th>
                        <th><spring:message code="label.tasks.date"/></th>
                        <th><spring:message code="label.tasks.description"/></th>
                        <th><spring:message code="label.tasks.category"/></th>
                    </tr>
                    <c:forEach items="${unchecked}" var="task">
                        <tr>
                            <td><a href="<spring:url
							value="/tasks/${task.id}"/>">${task.label}</a></td>
                            <td>${task.dateToExecute}</td>
                            <td>${fn:substring(task.description, 0, 75).concat("...")}</td>
                            <td>${task.category}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
</div>

</body>
</html>