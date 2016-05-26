<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Greatest Game Ever</title>
    </head>
    <body>
        <div>
            <h1>Lucky Sevens</h1>
            <form method="POST" action="/Controller"><label id="startingBetLabel" for="startingBet">Starting Bet:</label>
                <input type="text" id="startingBet" name="startingBet" value="$0.00" />
                <br />
                <input type="Submit" name="play" id="playButton" value="Play" />
            </form>
        </div>
    </body>
</html>