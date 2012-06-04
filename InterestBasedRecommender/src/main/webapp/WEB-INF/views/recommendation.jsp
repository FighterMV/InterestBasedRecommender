<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <ul>
	    <c:foreach items="${recommendations}" var="recommendation">
		<li><a href="${recommendation.item.link}">${recommendation.item.name}</a></li>
	    </c:foreach>
	</ul>
    </body>
</html>
