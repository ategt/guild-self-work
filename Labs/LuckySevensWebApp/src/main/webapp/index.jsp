<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Greatest Game Ever</title>
    </head>
    <body>
        <div align="center">
            <h1>Lucky Sevens</h1>
            <form method="POST" action="Controller"><label id="startingBetLabel" for="startingBet">Starting Bet:</label>
                <input type="text" id="startingBet" name="startingBet" value="$0.00" />
                <c:if test="inputInvalid">The Value Entered In This Field Is Not Valid</c:if>
                <br />
                <input type="Submit" name="play" id="playButton" value="Play" />
            </form>
        </div>
    </body>
</html>