/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $('#create-submit').on('click', function (e) {

        e.preventDefault();
        //console.log(contextRoot);
        var postableUrl = contextRoot + "/FlooringMaster/";
        //console.log(postableUrl);

        var orderData = JSON.stringify({
            name: $("#name").val(),
            state: $("#state").val(),
            product: $("#product").val(),
            date: $("#datepicker").val(),
            area: $("#area").val()
        });

        $.ajax({
            url: postableUrl,
            type: "POST",
            data: orderData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                var tableRow = buildOrderRow(data);

                $('#order-table').append($(tableRow));

            },
            error: function (data, status) {
                alert("error");
            }


        });


    });

    function buildOrderRow(data) {

    var strRowTable = "<tr id=\"order-row-" + data.id + "\" >\n\
                                \n\
        <td>" + data.id + "</td>\n\
        <td><a data-order-id=\"" + data.id + "\" data-toggle=\"modal\" data-target=\"#showDetailModal\">" + data.id + "</a></td>\n\
        <td><a href=\"contact/show/" + data.id + "\">" + data.name + "</a></td>\n\
        <td><a data-order-id=\"" + data.id + "\" data-toggle=\"modal\" data-target=\"#editDetailModal\">Edit</a></td>\n\
        <td><a data-order-id=\"" + data.id + "\" class=\"delete-link\">Delete</a></td>\n\
                                                                                        \n\
        </tr>";



        var strTableRow = "  <tr>\n\
                                <td><a href=\"/FlooringMaster/show/" + data.id + "\">" + data.id + "</a></td>\n\
                                <td><a href=\"/FlooringMaster/show/" + data.id + "\">" + data.name + "</a></td>\n\
                                <td><a href=\"/FlooringMaster/edit/" + data.id + "\">Edit</a></td>\n\
                                <td><a href=\"/FlooringMaster/delete/" + data.id + "\">Delete</a></td>\n\
\n\
                            </tr>";


        return strRowTable;

    }



    $('#showDetailModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var orderId = link.data('order-id');

        $.ajax({
            url: contextRoot + "/FlooringMaster/" + orderId,
            type: "GET",
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {


                $('#order-name').text(data.name);
                $('#order-state').text(data.state.stateName);
                $('#order-product').text(data.product.productName);
                $('#order-date').text(data.date);
                $('#order-area').text(data.area);
                $('#order-id').text(data.id);
                //$('#order-zipcode').text(data.zip);

               // $('.edit-from-detail-button').data("order-id", data.id);

            },
            error: function (data, status) {

            }

        });

    });

    $('#editDetailModal').on('show.bs.modal', function (e) {

//            firstName: $("#firstName").val(),
//            lastName: $("#lastName").val(),
//            streetNumber: $("#streetNumber").val(),
//            streetName: $("#streetName").val(),
//            city: $("#city").val(),
//            state: $("#state").val(),
//            zip: $("#zip").val()







        var link = $(e.relatedTarget);

        var orderId = link.data('order-id');

          $.ajax({
            url: contextRoot + "/FlooringMaster/" + orderId,
            type: "GET",
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json")
            },
            success: function (data, status) {
                $('#edit-order-name').val(data.name);
                $('#edit-order-state').val(data.state.stateName);
                $('#edit-order-product').val(data.product.productName);
                $('#edit-order-date').val(data.date);
                $('#edit-order-area').val(data.area);
                //$('#edit-order-id').val(data.id);
                //$('#edit-order-zipcode').val(data.zip);
                $('#edit-id').val(data.id);
                
//                var lastContacted = data.lastContacted;
//                if (data.lastContacted == null) {
//                    lastContacted = new Date();
//                }
//
//                $('#edit-contact-last-contacted').val(lastContacted.toLocaleDateString());

            },
            error: function (data, status) {

            }

        });

    });

    $('#edit-order-button').on('click', function (e) {

        e.preventDefault();

        var orderData = JSON.stringify({
            id: $("#edit-id").val(),
            name: $("#edit-order-name").val(),
            product: $("#edit-order-product").val(),
            date: $("#edit-order-date").val(),
            //streetName: $("#edit-order-street-name").val(),
            //city: $("#edit-order-city").val(),
            state: $("#edit-order-state").val(),
            area: $("#edit-order-area").val()
        });


        $.ajax({
            url: contextRoot + "/FlooringMaster/",
            type: "PUT",
            data: orderData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                $('#editDetailModal').modal('hide');

                var tableRow = buildOrderRow(data);

                $('#order-row-' + data.id).replaceWith($(tableRow));

            },
            error: function (data, status) {
                alert("error");
            }



        });

    });

    $(document).on('click', '.delete-link', function (e) {

        e.preventDefault();

        var orderId = $(e.target).data('order-id');

        $.ajax({
            type: "DELETE",
            url: contextRoot + "/FlooringMaster/" + orderId,
            success: function (data, status) {
                $('#order-row-' + orderId).remove();
            },
            error: function (data, status) {

            }

        });


    });














});