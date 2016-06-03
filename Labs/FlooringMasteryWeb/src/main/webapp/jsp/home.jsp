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
            <!--            <div class="navbar">
                            <ul class="nav nav-tabs">
                                <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                                <li role="presentation"><a href="${pageContext.request.contextPath}/search">Search</a></li>
            
                            </ul>    
                        </div>-->



            <%@ include file="banner.jspf" %>


            <div class="row">
                <div class="col-md-6">

                    <table class="table table-hover">
                        <th>Order Number</th>
                        <th>Order Name</th>
                        <th><i class="glyphicon glyphicon-edit"></i> Edit</th>
                        <th><i class="glyphicon glyphicon-remove"></i> Delete</th>
                            <c:forEach items="${orders}" var="order">
                            <tr>
                                <td><a href="FlooringMaster/show/${order.id}">${order.id}</a></td>
                                <td><a href="FlooringMaster/show/${order.id}">${order.name}</a></td>
                                <td><a href="FlooringMaster/edit/${order.id}">Edit</a></td>
                                <td><a href="FlooringMaster/delete/${order.id}">Delete</a></td>

                            </tr>
                        </c:forEach>



                    </table>




                </div>
                <div class="col-md-6">
                    <form:form method="POST" commandName="newOrder" action="${pageContext.request.contextPath}/FlooringMaster/create" class="form-horizontal">
                        <div class="form-group">
                            <form:label path="name" for="name" class="col-sm-2 control-label" >Order Name:</form:label>
                                <div class="col-sm-10">
                                <form:input path="name" class="form-control" type="text" name="name" id="name" placeholder="Order Name" />
                            </div>
                        </div>
                        <div class="form-group">

                            <form:label path="state" for="state" class="col-sm-2 control-label" >State:</form:label>
                                <div class="col-sm-10">
                                <form:input path="state" class="form-control" type="text" name="state" id="state" placeholder="State" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="product" for="product" class="col-sm-2 control-label" >Product:</form:label>
                                <div class="col-sm-10">
                                <form:input path="product" class="form-control" type="text" name="product" id="product" placeholder="Product" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="date" for="date" class="col-sm-2 control-label" >Date:</form:label>
                                <div class="col-sm-10">
                                <form:input path="date" class="form-control" type="text" name="date" id="date" placeholder="Order Date" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="area" for="area" class="col-sm-2 control-label" >Area:</form:label>
                                <div class="col-sm-10">
                                <form:input path="area" class="form-control" type="text" name="area" id="area" placeholder="Area" />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-10">
                                <input value="Create" type="submit" class="btn btn-default" />
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

