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
                <tr class="title">
                    <td>Number To Be Factorized:</td>
                    <td>${numberToBeFactorized}</td>
                </tr>
                <tr class="title">
                    <td>Number of Factors:</td>
                    <td>${numberOfFactors}</td>
                </tr>
                <tr class="title">
                    <td>Is This A Prime Number:</td>
                    <td>${isPrime}</td>
                </tr>
                <tr class="title">
                    <td>Is This A Perfect Number:</td>
                    <td>${isPerfect}</td>
                </tr>



            </table>
            <div id="factors">
                <c:forEach items="${factors}" var="factor">

                    ${factor} <br />

                </c:forEach> 
            </div>

        </div>    
    </body>
</html>
