
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Temperature Converter</title>
    </head>
    <body>
        <div align="center">


            <form method="POST" action="TemperatureController">
                <table border="1" cellpadding="5">
                    <caption>
                        <h1>Temperature Converter</h1>
                        <c:if test="${hadError}">
                            <h2>The System Was Unable To Calculate The Information Needed Using Those Values</h2>
                        </c:if>
                    </caption>
                    <tr>
                        <td>
                            <label id="tempFromConversionTypeLabel" for="tempFromConversionType">
                                Temperature Unit To Convert From:
                            </label>
                        </td>
                        <td>
                            <select id="tempFromConversionType" name="tempFromConversionType">
                                <c:forEach items="${temperatureFromUnits}" var="temperatureFromUnit">
                                    <option value="${temperatureFromUnit} ">${temperatureFromUnit}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label id="tempToConversionTypeLabel" for="tempToConversionType">
                                Temperature Unit To Convert Into:
                            </label>
                        </td>
                        <td>
                            <select id="tempToConversionType" name="tempToConversionType">
                                <c:forEach items="${temperatureToUnits}" var="temperatureToUnit">
                                    <option value="${temperatureToUnit} ">${temperatureToUnit}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label id="tempToConvertLabel" for="tempToConvert">
                                Temperature Unit To Convert Into:
                            </label>
                        </td>
                        <td>
                            <input type="text" id="tempToConvert" name="tempToConvert" />
                        </td>
                    </tr>







                    <!--
                    ////    Fahrenheit	[°F] = [°C] × 9⁄5 + 32  	[°C] = ([°F] − 32) × 5⁄9
////    Kelvin      [K] = [°C] + 273.15             [°C] = [K] − 273.15
////    Rankine	[°R] = ([°C] + 273.15) × 9⁄5	[°C] = ([°R] − 491.67) × 5⁄9
////    Delisle	[°De] = (100 − [°C]) × 3⁄2	[°C] = 100 − [°De] × 2⁄3
////    Newton	[°N] = [°C] × 33⁄100            [°C] = [°N] × 100⁄33
////    Réaumur	[°Ré] = [°C] × 4⁄5              [°C] = [°Ré] × 5⁄4
////    Rømer	[°Rø] = [°C] × 21⁄40 + 7.5	[°C] = ([°Rø] − 7.5) × 40⁄21

                    
                    
                    
                    
                    <option value="temperature">Temperature</option>    
                    <option value="currency">Currency</option>
                    <option value="volume">Volume</option>
                    <option value="mass">Mass</option>
                    -->



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
