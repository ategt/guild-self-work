
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flooring Calculator</title>
    </head>
    <body>
        <div align="center">
            <table border="1" cellpadding="10">
                <caption>
                    <h1>Flooring Calculator</h1>
                </caption>
                <tr>
                    <td>Floor Area</td>
                    <td>${floorArea} SQFT</td>
                </tr>
                <tr>
                    <td>Labor Cost</td>
                    <td>${laborCost}</td>
                </tr>
                <tr>
                    <td>Material Cost</td>
                    <td>${materialCost}</td>
                </tr>
                <tr>
                    <td>Total Flooring Cost</td>
                    <td>${totalFloorCost}</td>
                </tr>

            </table>
        </div>



    </body>
    <!--</html>
            <h1>${message}</h1>
            
    <c:forEach items="${numbers}" var="number">
        ${number} <br />
    </c:forEach>
    
    <c:if test="${isGoing}">
        This is true.
    </c:if>
    
    <c:if test="${isGoing}">
        This is true.
    </c:if>      -->
</html>