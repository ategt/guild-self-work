
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
            <c:if test="${hadError}">
                <h1>The System Was Unable To Calculate The Information Needed Using Those Values</h1>
            </c:if>


            <form method="POST" action="FlooringCalculator">
                <table border="1" cellpadding="5">
                    <caption>
                        <h1>Flooring Calculator</h1>
                    </caption>
                    <tr>
                        <td>
                            <label id="floorLengthLabel" for="floorLength">
                                Floor Length:
                            </label>
                        </td>
                        <td>
                            <input type="text" id="floorLength" name="floorLength" value="${oldLength}" />
                            <c:if test="${lengthError}">
                                <td>
                                <b>This Value is not Supported.</b>
                                </td>
                            </c:if>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="floorWidthLabel" for="floorWidth">
                                Floor Width:
                            </label>
                        </td>
                        <td>
                            <input type="text" id="floorWidth" name="floorWidth" value="${oldWidth}" />
                            <c:if test="${widthError}">
                                <td>
                                <b>This Value is not Supported.</b>
                                </td>
                            </c:if>

                        </td>
                    </tr>
                    <tr>
                        <td>

                            <label id="floorUnitCostLabel" for="floorUnitCost">
                                Floor Unit Cost:
                            </label>
                        </td>
                        <td>
                            <input type="text" id="floorUnitCost" name="floorUnitCost" value="${oldUnitCost}" />
                            <c:if test="${unitCostError}">
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
