<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Central Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body {
                background-color: lightskyblue;
            }
            #header, #footer {
                text-align: center;
                border: 5px solid black;
                background-color: orangered;
            }
            #footer{
                vertical-align: bottom;
            }
            
        </style>
    </head>
    <body>
        <div id="header">
            <div id="titleField" align="center" style="text-align: center;border: 5px solid black; background-color: red">
                <h1>Software Craftsmanship Guild Java Cohort</h1>
                <h1>JSP Site Lab</h1>
            </div>
            <div id="navField" align="center" style="text-align: center;border: 5px solid black; background-color: red; margin: 5px;">
                <a href="http://localhost:8080/CentralPage.html">Home</a> |
                <a href="http://localhost:8080/LuckySevensWebApp/Controller">Lucky Sevens</a> |
                <a href="http://localhost:8080/Factorizor/Controller">Factorizor</a> |
                <a href="http://localhost:8080/InterestCalculator/InterestCalculator">Interest Calculator</a> |
                <a href="http://localhost:8080/FlooringCalculator/FlooringCalculator">Flooring Calculator</a> |
                <a href="http://localhost:8080/TipCalculator/TipCalcController">Tip Calculator</a> |
                <a href="http://localhost:8080/Converter/ConverterController">Unit Converter</a>
            </div>
        </div>
        <div align="center">
            <h1>Lucky Sevens</h1>
            <form method="POST" action="Controller"><label id="startingBetLabel" for="startingBet">Starting Bet:</label>
                <input type="text" id="startingBet" name="startingBet" value="$0.00" />
                <c:if test="inputInvalid">The Value Entered In This Field Is Not Valid</c:if>
                <br />
                <input type="Submit" name="play" id="playButton" value="Play" />
            </form>
        </div>
                 <div id="footer">
            <div id="footerField" align="center" style="text-align: center;border: 5px solid black; background-color: red">
                <h3>Created By: Adam Tegtmeier 2016</h3>
                <h3>Powered By Java</h3>
            </div>
        </div>
    </body>
</html>