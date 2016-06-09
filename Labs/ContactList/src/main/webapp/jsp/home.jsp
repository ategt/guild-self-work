<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

                    <table id="contact-table" class="table table-hover">
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th><i class="glyphicon glyphicon-edit"></i> Edit</th>
                        <th><i class="glyphicon glyphicon-remove"></i> Delete</th>
                            <c:forEach items="${contacts}" var="contact">
                            <tr id="contact-row-${contact.id}" >
                                
                                <td>${contact.id}</td>
                                <td><a data-contact-id="${contact.id}" data-toggle="modal" data-target="#showContactModal">${contact.firstName}</a></td>
                                <td><a href="contact/show/${contact.id}">${contact.lastName}</a></td>
                                <td><a data-contact-id="${contact.id}" data-toggle="modal" data-target="#editContactModal">Edit</a></td>
                                <td><a data-contact-id="${contact.id}" class="delete-link">Delete</a></td>

                            </tr>
                        </c:forEach>



                    </table>




                </div>
                <div class="col-md-6">
                    <form method="POST" class="form-horizontal">
                        <div class="form-group">
                            <label for="firstName" class="col-sm-2 control-label" >First:</label>
                            <div class="col-sm-10">
                                <input name="firstName" class="form-control" type="text" id="first-name-input" placeholder="First Name"  />
                                <span name="firstName" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="col-sm-2 control-label" >Last:</label>
                            <div class="col-sm-10">
                                <input name="lastName" class="form-control" type="text" id="last-name-input" placeholder="Last Name" />
                                <span name="lastName" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="company" class="col-sm-2 control-label" >Company:</label>
                            <div class="col-sm-10">
                                <input name="company" class="form-control" type="text" id="company-input" placeholder="Company" />
                                <span name="company" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Email" class="col-sm-2 control-label" >Email:</label>
                            <div class="col-sm-10">
                                <input name="email" class="form-control" type="text" id="email-input" placeholder="Email" />
                                <span name="email" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-sm-2 control-label" >Phone:</label>
                            <div class="col-sm-10">
                                <input name="phone" class="form-control" type="text" id="phone-input" placeholder="Phone" />
                                <span name="phone" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastContacted" class="col-sm-2 control-label" >Last Contacted:</label>
                            <div class="col-sm-10">
                                <input class="form-control" type="text" name="lastContacted" id="last-contacted-input" placeholder="Last Contacted" />
                                <span name="lastContacted" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-10">
                                <div id="add-contact-validation-errors"></div>
                                <input id="create-sumbit" type="submit" class="btn btn-default" />
                            </div>
                        </div>


                    </form>
                </div>
            </div>






        </div>
    </div>

    <div id="showContactModal" class="modal fade" role="dialog">
          <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Contact Details</h4>
              </div>
              <div class="modal-body">

                  <table class="table table-bordered" id="show-contact-table">
                      <tr>
                          <th>First Name:</th>
                          <td id="contact-first-name"></td>
                      </tr>
                      
                      <tr>
                          <th>Last Name:</th>
                          <td id="contact-last-name"></td>
                      </tr>
                      
                      <tr>
                          <th>Company</th>
                          <td id="contact-company"></td>
                      </tr>
                      
                      <tr>
                          <th>Email:</th>
                          <td id="contact-email"></td>
                      </tr>
                      
                      <tr>
                          <th>Phone:</th>
                          <td id="contact-phone"></td>
                      </tr>
                      
                      <tr>
                          <th>Last Contacted:</th>
                          <td id="contact-last-contacted"></td>
                      </tr>
                      
                      
                      
                      
                  </table>

              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>

          </div>
        </div>


    <div id="editContactModal" class="modal fade" role="dialog">
          <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit Contact Details</h4>
              </div>
              <div class="modal-body">

                  <table class="table table-bordered">
                      
                      <tr>
                          <th>ID:</th>
                          <td>
                              <input type="text" id="edit-id" />
                          </td>
                      </tr>
                      
                      <tr>
                          <th>First Name:</th>
                          <td>
                              <input type="text" id="edit-contact-first-name" />
                          </td>
                      </tr>
                      
                      <tr>
                          <th>Last Name:</th>
                          <td>
                              <input type="text" id="edit-contact-last-name" />
                          </td>
                      </tr>
                      
                      <tr>
                          <th>Company:</th>
                          <td>
                              <input type="text" id="edit-contact-company" />
                          </td>
                      </tr>
                      
                      <tr>
                          <th>Email:</th>
                          <td>
                              <input type="text" id="edit-contact-email" />
                          </td>
                      </tr>
                      
                      <tr>
                          <th>Phone:</th>
                          <td><input type="text" id="edit-contact-phone" /></td>
                      </tr>
                      
                      <tr>
                          <th>Last Contacted:</th>
                          <td> <input type="text" id="edit-contact-last-contacted" /></td>
                      </tr>
                      
                      
                      
                      
                  </table>

              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-danger" id="edit-contact-button">Save</button>
              </div>
            </div>

          </div>
        </div>




    <script>
        var contextRoot = "${pageContext.request.contextPath}";
    </script>

    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/app.js"></script>

</body>
</html>

