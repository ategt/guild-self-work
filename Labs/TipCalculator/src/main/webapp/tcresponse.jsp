
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tip Calculator</title>
    </head>
    <body>
        <div align="center">
            <table border="1" cellpadding="10">
                <caption>
                    <h1>Tip Calculator</h1>
                </caption>
                <tr>
                    <td>Bill Amount:</td>
                    <td>${billValue}</td>
                </tr>
                <tr>
                    <td>Tip %:</td>
                    <td>${tipPercentage}</td>
                </tr>
                <tr>
                    <td>Tip:</td>
                    <td>${tipValue}</td>
                </tr>
                <tr>
                    <td>Total:</td>
                    <td>${totalValue}</td>
                </tr>

            </table>
        </div>



    </body>
</html>