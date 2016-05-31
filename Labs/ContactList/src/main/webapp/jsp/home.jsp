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
                <div class="col-md-6">

                    <table class="table table-hover">
                        <c:forEach items="${contacts}" var="contact">
                            <tr>
                                <td>${contact.firstName}</td>
                                <td>${contact.lastName}</td>
                                <td><a href="contact/edit?id=${contact.id}">Edit</a></td>
                                <td><a href="contact/delete?id=${contact.id}">Delete</a></td>

                            </tr>
                        </c:forEach>



                    </table>




                </div>
                <div class="col-md-6">
                    <form method="POST" action="contact/create" class="form-horizontal">
                        <div class="form-group">
                            <label for="firstName" class="col-sm-3 control-label" >First:</label>
                            <div class="col-sm-9">
                                <input class="form-control" type="text" name="firstName" id="firstName" placeholder="First Name" />
                            </div>
                        </div>
                        <div class="form-group">

                            <label for="lastName" class="col-sm-3 control-label" >Last:</label>
                            <div class="col-sm-9">
                                <input class="form-control" type="text" name="lastName" id="lastName" placeholder="Last Name" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="company" class="col-sm-3 control-label" >Company:</label>
                            <div class="col-sm-9">
                                <input class="form-control" type="text" name="company" id="company" placeholder="Company" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-sm-3 control-label" >Phone:</label>
                            <div class="col-sm-9">
                                <input class="form-control" type="text" name="phone" id="phone" placeholder="Phone" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9">
                                <input type="submit" class="btn" />
                            </div>
                        </div>

                </div>
                </form>
            </div>






        </div>
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>

