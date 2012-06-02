<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form:form action="store-and-recommend-user.html" commandName="storeAndRecommendUserModel" method="POST">
	    <table>
		<tr>
		    <td>Username: </td>
		    <td><form:input path="username"/></td>
		</tr>
		<tr>
		    <td>Interests (separate by comma): </td>
		    <td><form:input path="interests"/></td>
		</tr>
		<tr>
		    <td>Weightings (separate by comma): </td>
		    <td><form:input path="weightings"/></td>
		</tr>
	    </table>
	    
	    <input type="submit"/>
	    
	</form:form>
    </body>
</html>
