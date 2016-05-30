
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unit Converter</title>
    </head>
    <body>
        <div align="center">


            <form method="POST" action="ConverterController">
                <table border="1" cellpadding="5">
                    <caption>
                        <h1>Unit Converter</h1>
                        <c:if test="${hadError}">
                            <h2>The System Was Unable To Calculate The Information Needed Using Those Values</h2>
                        </c:if>
                    </caption>
                    <tr>
                        <td>
                            <label id="conversionTypeLabel" for="conversionType">
                                Conversion Type:
                            </label>
                        </td>
                        <td>

                            <select id="conversionType" name="conversionType">

                                <option value="temperature">Temperature</option>    
                                <option value="currency">Currency</option>
                                <option value="volume">Volume</option>
                                <option value="mass">Mass</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="Submit" name="calculateButton" id="calculateButton" value="Next" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
