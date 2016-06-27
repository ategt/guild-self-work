<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
 
<body> 

<h2>Spring MVC - Uploading a file.. </h2>
	<form:form method="POST" commandName="file"	enctype="multipart/form-data">
 
		Upload your file please: 
		<input type="file" name="file" />
		<input type="submit" value="upload" />
		<form:errors path="file" cssStyle="color: #ff0000;" />
	</form:form>
                
          
</body>
</html>