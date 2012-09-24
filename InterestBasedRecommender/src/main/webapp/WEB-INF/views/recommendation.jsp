<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
	<h2>Matching items from similar users</h2>
        <ul>
	    <c:forEach items="${recommendation.itemRecommendations}" var="recommendation">
		<li><a href="${recommendation.item.link}" target="blank">${recommendation.item.name}</a></li>
	    </c:forEach>
	</ul>
	
	<h2>Other items from similar users</h2>
	<ul>
	    <c:forEach items="${recommendation.itemsBySimilarUsers}" var="recommendation">
		<li><a href="${recommendation.item.link}" target="blank">${recommendation.item.name}</a></li>
	    </c:forEach>
	</ul>
	
	<h2>Similar users</h2>
	<ul>
	    <c:forEach items="${recommendation.similarUsers}" var="user">
		<li><a href="${user.link}" target="blank">${user.name}</a></li>
	    </c:forEach>
	</ul>
    </body>
</html>
