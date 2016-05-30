
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tip Calculators</title>
    </head>
    <body>
        <div align="center">


            <form method="POST" action="TipCalcController">
                <table border="1" cellpadding="5">
                    <caption>
                        <h1>Tip Calculators</h1>
            <c:if test="${hadError}">
                <h2>The System Was Unable To Calculate The Information Needed Using Those Values</h2>
            </c:if>
                    </caption>
                    <tr>
                        <td>
                            <label id="billValueLabel" for="billValue">
                                Amount:
                            </label>
                        </td>
                        <td>
                            <input type="text" id="billValue" name="billValue" value="${billValueOutput}" />
                            <c:if test="${billValueError}">
                                <td>
                                <b>This Value is not Supported.</b>
                                </td>
                            </c:if>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="tipPercentageLabel" for="tipPercentage">
                                Tip Percentage:
                            </label>
                        </td>
                        <td>
                            <input type="text" id="tipPercentage" name="tipPercentage" value="${tipPercentageOutput}" />
                            <c:if test="${tipPercentageError}">
                                <td>
                                <b>This Value is not Supported.</b>
                                </td>
                            </c:if>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="Submit" name="calculateButton" id="calculateButton" value="Calculate" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
