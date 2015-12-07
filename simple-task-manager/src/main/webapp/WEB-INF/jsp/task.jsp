<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title><spring:message code="label.tasks.title"/></title>
</head>
<body>

<h1 class="alert-info"><spring:message code="label.tasks.viewPageTitle"/></h1>

<div class="container">
            <div class="form-group">
            <label for="task-label"><spring:message code="label.tasks.label"/></label>
            <span>${task.label}</span>
        </div>

        <div class="form-group">
            <label for="task-date"><spring:message code="label.tasks.date"/></label>
            <span>${task.dateToExecute}</span>
        </div>

        <div class="form-group">
            <label for="task-category"><spring:message code="label.tasks.category"/></label>
            <span>${task.category}</span>
        </div>

        <div class="form-group">
            <label for="task-description"><spring:message code="label.tasks.description"/></label>
            <span>${task.description}</span>
        </div>

    <form action="/tasks/${task.getId()}/check" method="get">
        <input type="submit" value="Mark as checked"
               name="check" id="check" />
    </form>

</div>

</body>
</html>
