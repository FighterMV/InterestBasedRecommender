<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <ul>
	    <c:forEach items="${recommendations}" var="recommendation">
		<li><a href="${recommendation.item.link}">${recommendation.item.name}</a></li>
	    </c:forEach>
	</ul>
    </body>
</html>
