<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Landing Page</title>
    </head>
  //  <body>
        //<h1>You should not see this.</h1>
        
        
        <body onload="hideResultsTable()">
		<div>
			<h1>Lucky Sevens</h1>
			<form onsubmit="playLuckySevens();return false;" method="#" action="LuckySevens.html">
				<label id="startingBetLabel" for="startingBet">Starting Bet:</label>
				<input type="text" id="startingBet" name="startingBet" onfocus="focusedOnStartingBet();" onblur="bluredOnStartingBet();" value="$0.00" />
				<br />
				<input type="button" name="play" id="playButton" onclick="playLuckySevens();" value="Play" />
			</form>
		</div>
		<div id="resultsDiv">
			<table id="resultsTable">
				<caption>Results</caption>
				<tr id="startingBetRow">
					<td class="description">Starting Bet</td>
					<td class="value" id="startingBetValue">$###.##</td>
				</tr>
				<tr>
					<td class="description">Total Rolls Before Going Broke</td>
					<td class="value" id="totalRolls">#####</td>
				</tr>
				<tr>
					<td class="description">Highest Amount Won</td>
					<td class="value" id="highestAmountWon">$###.##</td>
				</tr>

				<tr>
					<td class="description">Roll Count at Highest Amount Won</td>
					<td class="value" id="rollCountAtHighestAmount">#####</td>
				</tr>
			</table>
		</div>
	</body>
</html>