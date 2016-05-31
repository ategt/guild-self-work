<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factorizer</title>
    </head>
    <body>
        <div align="center">
            <h1>Factorizer</h1>
            <form method="POST" action="Controller">
                <label id="factorizeNumberLabel" for="startingBet">
                    Please Enter A Number To Be Factorized:
                </label>
                <br />
                <c:if test="${inputInvalid}">
                    <h3>The Number Input Could Not Be Factored</h3>
                </c:if>
                <input type="text" id="factorizeNumber" name="factorizeNumber" value="0" />
                <br />
                <input type="Submit" name="factorizeButton" id="factorizeButton" value="Factorize!" />
            </form>
        </div>
    </body>
</html>