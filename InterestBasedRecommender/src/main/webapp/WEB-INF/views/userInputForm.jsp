<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form:form action="store-and-recommend-user" commandName="storeAndRecommendUserModel" method="POST">
	    
	    <form:input path="storeAndRecommendUserModel.username"/>
	    <form:input path="storeAndRecommendUserModel.interests"/>
	    <form:input path="storeAndRecommendUserModel.weightings"/>
	    
	    <input type="submit"/>
	    
	</form:form>
    </body>
</html>
