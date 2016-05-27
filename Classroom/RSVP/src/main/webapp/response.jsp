
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${message}</h1>
        
        <c:forEach items="${numbers}" var="number">
            ${number} <br />
        </c:forEach>
        
            <c:if test="${isGoing}">
                This is true.
            </c:if>
            
            <c:if test="${isGoing}">
                This is true.
            </c:if>
            
    </body>
</html>
