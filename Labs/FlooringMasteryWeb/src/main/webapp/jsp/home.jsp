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


        <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />


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

                    <table id="order-table" class="table table-hover">
                        <th>Order Number</th>
                        <th>Order Name</th>
                        <th><i class="glyphicon glyphicon-edit"></i> Edit</th>
                        <th><i class="glyphicon glyphicon-remove"></i> Delete</th>
                            <c:forEach items="${orders}" var="order">
                            <tr>
                                <td><a href="${pageContext.request.contextPath}/FlooringMaster/show/${order.id}">${order.id}</a></td>
                                <td><a href="${pageContext.request.contextPath}/FlooringMaster/show/${order.id}">${order.name}</a></td>
                                <td><a href="${pageContext.request.contextPath}/FlooringMaster/edit/${order.id}">Edit</a></td>
                                <td><a href="${pageContext.request.contextPath}/FlooringMaster/delete/${order.id}">Delete</a></td>

                            </tr>
                        </c:forEach>



                    </table>




                </div>
                <div class="col-md-6">
                    <form method="POST" class="form-horizontal">
                        <!--<input type=hidden path="id" />-->


                        <div class="form-group">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9 text-center">
                                <strong><span class="name" /></strong>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label" >Order Name:</label>
                            <div class="col-sm-9">
                                <input name="name" class="form-control" style="text-align: center" type="text" id="name" placeholder="Order Name" />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9 text-center">
                                <strong><span class="state" /></strong>
                            </div>
                        </div>
                        <div class="form-group">

                            <label path="state" for="state" class="col-sm-3 control-label" >State:</label>
                            <div class="col-sm-9">
                                <input name="state" class="form-control" style="text-align: center" type="text" id="state" placeholder="State" />
                            </div>
                        </div>



                        <div class="form-group">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9 text-center">
                                <strong><span class="product" /></strong>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product" class="col-sm-3 control-label" >Product:</label>
                            <div class="col-sm-9">
                                <input name="product" class="form-control" style="text-align: center" type="text" id="product" placeholder="Product" />
                            </div>
                        </div>




                        <div class="form-group">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9 text-center">
                                <strong><span class="date" /></strong>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="date" class="col-sm-3 control-label" >Date:</label>
                            <div class="col-sm-9">
                                <input name="date" class="form-control" style="text-align: center" type="text" id="datepicker" placeholder="Order Date" />
                            </div>
                        </div>




                        <div class="form-group">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9 text-center">
                                <strong><span class="area" /></strong>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="area" class="col-sm-3 control-label" >Area:</label>
                            <div class="col-sm-9">
                                <input name="area" class="form-control" style="text-align: center" type="text" id="area" placeholder="Area" />
                            </div>
                        </div>




                        <div class="form-group">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9 text-center">
                                <input value="Create" id="create-submit" type="submit" class="btn btn-default" />
                            </div>
                        </div>

                </div>
                </form>
            </div>






        </div>
    </div>


    <script>
        $(function () {
            $("#datepicker").datepicker();
        });
    </script>
    <script>
        var contextRoot = "${pageContext.request.contextPath}";
    </script>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
    <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

</body>
</html>

