<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results Page</title>
    </head>
    <body>
        <div id="resultsDiv">
            <table id="resultsTable">
                <caption>Results</caption>
                <tr id="startingBetRow">
                    <td class="description">Starting Bet</td>
                    <td class="value" id="startingBetValue">${startingBetValue}</td>
                </tr>
                <tr>
                    <td class="description">Total Rolls Before Going Broke</td>
                    <td class="value" id="totalRolls">${totalRolls}</td>
                </tr>
                <tr>
                    <td class="description">Highest Amount Won</td>
                    <td class="value" id="highestAmountWon">${highestAmountWon}</td>
                </tr>

                <tr>
                    <td class="description">Roll Count at Highest Amount Won</td>
                    <td class="value" id="rollCountAtHighestAmount">${rollCountAtHighestAmount}</td>
                </tr>
            </table>
        </div>    
    </body>
</html>
