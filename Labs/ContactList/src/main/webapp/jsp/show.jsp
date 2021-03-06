<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Contact List</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Contact List</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>


                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6 text-center">
                    <table class="table table-hover">
                        <tr>
                            <td> 
                                First: 
                            </td>
                            <td>
                                ${contact.firstName}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Last: 
                            </td>
                            <td>
                                ${contact.lastName}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Company: 
                            </td>
                            <td>
                                ${contact.company}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                E-Mail: 
                            </td>
                            <td>
                                ${contact.email}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Phone: 
                            </td>
                            <td>
                                ${contact.phone}
                            </td>
                        </tr>
                    </table>

                </div>
            </div>






        </div>
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>

