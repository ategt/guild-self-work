<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<h2>Spring MVC - Uploading a file.. </h2>
<p>Your file is successfully uploaded.</p>
<p>Path: ${filePath}</p>
<p>Original Name: ${originalName}</p>
<p>Content Type: ${contentType}</p>
<p>File Size: ${fileSize}</p>
<p>File Name: ${multipartFileName}</p>

</body>
</html>