<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Flooring Master</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Flooring Master</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/search">Search</a></li>

                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">

                    <table class="table table-hover">
                        <th>Order Number</th>
                        <th>Order Name</th>
                        <th><i class="glyphicon glyphicon-edit"></i> Edit</th>
                        <th><i class="glyphicon glyphicon-remove"></i> Delete</th>
                            <c:forEach items="${orders}" var="order">
                            <tr>
                                <td><a href="addressbook/show/${order.id}">${order.id}</a></td>
                                <td><a href="addressbook/show/${order.id}">${order.name}</a></td>
                                <td><a href="addressbook/edit/${order.id}">Edit</a></td>
                                <td><a href="addressbook/delete/${order.id}">Delete</a></td>

                            </tr>
                        </c:forEach>



                    </table>




                </div>
                <div class="col-md-6">
                    <form:form method="POST" commandName="order" action="${pageContext.request.contextPath}/addressbook/create" class="form-horizontal">
                        <div class="form-group">
                            <form:label path="" for="name" class="col-sm-2 control-label" >First:</label>
                            <div class="col-sm-10">
                                <form:input path="" class="form-control" type="text" name="firstName" id="firstName" placeholder="First Name" />
                            </div>
                        </div>
                        <div class="form-group">

                            <form:label path="" for="state" class="col-sm-2 control-label" >Last:</label>
                            <div class="col-sm-10">
                                <form:input path="" class="form-control" type="text" name="lastName" id="lastName" placeholder="Last Name" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="" for="product" class="col-sm-2 control-label" >Street Number:</label>
                            <div class="col-sm-10">
                                <form:input path="" class="form-control" type="text" name="streetNumber" id="streetNumber" placeholder="Street Number" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="" for="materialCost" class="col-sm-2 control-label" >Street Name:</label>
                            <div class="col-sm-10">
                                <form:input path="" class="form-control" type="text" name="streetName" id="streetName" placeholder="Street Name" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="" for="taxRate" class="col-sm-2 control-label" >City:</label>
                            <div class="col-sm-10">
                                <form:input path="" class="form-control" type="text" name="city" id="city" placeholder="City" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="" for="state" class="col-sm-2 control-label" >State:</label>
                            <div class="col-sm-10">
                                <form:input path="" class="form-control" type="text" name="state" id="state" placeholder="State" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="" for="zip" class="col-sm-2 control-label" >Zip:</label>
                            <div class="col-sm-10">
                                <form:input path="" class="form-control" type="text" name="zip" id="zip" placeholder="Zip" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-10">
                                <form:input path="" type="submit" class="btn btn-default" />
                            </div>
                        </div>

                </div>
                </form:form>
            </div>






        </div>
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>

