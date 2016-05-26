
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>I'm having a party. Can you attend?</h1>
        
        <form action='RSVPServlet' method='POST'>
            <label for="myAnswer">Answer</label><input type='text' name='myAnswer'><br />
            <label for="myReason">Reason</label><input type='text' name='myReason'><br />
            
            <input type="submit" value='Submit' />
            
        </form>
    </body>
</html>
