<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Welcome to task scheduler!</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/social-buttons-3.css"/>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.css" type="text/css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-theme.css" type="text/css"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#datepicker").datepicker();
            dateFormat: "dd-mm-yyyy"
            });
    </script>
</head>
<body>
<div class="container">

  <div class="row">
    <h1>Here you can add new task!</h1>
  </div>

  <spring:url value="/tasks/add" var="taskAddUrl"/>

  <form:form action="${taskAddUrl}" method="POST" commandName="task" enctype="utf8" role="form">

    <div class="row">

      <div class="form-group">
        <label for="label">Label for you task</label>
        <form:input path="label" maxlength="55" cssClass="form-control" id="label"/>
        <form:errors id="error-label" path="label" cssClass="help-block"/>
      </div>

      <div class="form-group">
        <label>Date to execute</label>
          <form:input path="dateTime" id="datepicker" />
        <form:errors id="error-dateTime" path="dateTime" cssClass="help-block"/>
      </div>

      <div class="form-group">
        <label for="category">Category of task</label>
        <form:select path="category" items="${categories}" cssClass="selectpicker"/>
      </div>

      <div class="form-group">
        <label for="notes">Description of task</label>
        <form:textarea id="notes" path="description"  class="form-control"  rows="3"/>
        <form:errors id="error-description" path="description" cssClass="help-block"/>
      </div>


      <button type="submit" class="btn btn-default">Submit</button>

    </div>

  </form:form>

</div>
</body>
</html>