<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results Page</title>
    </head>
    <body>
        <div id="resultsDiv" align="center">
            <table id="resultsTable">
                <caption>Results</caption>

                <tr>
                    <td>Year Number</td>
                    <td>Begining Balance</td>
                    <td>Total Interest For The Year</td>
                    <td>Balance At The End Of The Year</td>
                </tr>
                <c:forEach items="${tableRow}" var="listOfItems">

                    <tr>

                        ${listOfItems}

                    </tr>

                </c:forEach>
                <tr id="Title">

                </tr>
            </table>
        </div>    
    </body>
</html>
