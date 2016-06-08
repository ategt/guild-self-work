/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $('#create-submit').on('click', function (e) {

        e.preventDefault();

        var addressData = JSON.stringify({
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            streetNumber: $("#streetNumber").val(),
            streetName: $("#streetName").val(),
            city: $("#city").val(),
            state: $("#state").val(),
            zip: $("#zip").val()
        });


        firstName: $("#firstName").val('');
        lastName: $("#lastName").val('');
        streetNumber: $("#streetNumber").val('');
        streetName: $("#streetName").val('');
        city: $("#city").val('');
        state: $("#state").val('');
        zip: $("#zip").val('');
                $.ajax({
                    url: contextRoot + "/addressbook/",
                    type: "POST",
                    data: addressData,
                    dataType: 'json',
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader("Accept", "application/json");
                        xhr.setRequestHeader("Content-type", "application/json");
                    },
                    success: function (data, status) {

                        var tableRow = buildContactRow(data);

                        $('#address-table').append($(tableRow));

                    },
                    error: function (data, status) {
                        alert("error");
                    }


                });


    });

    function buildContactRow(data) {


        var strTableRow = "  <tr>\n\
                                <td><a href=\"addressbook/show/" + data.id + "\">" + data.firstName + "</a></td>\n\
                                <td><a href=\"addressbook/show/" + data.id + "\">" + data.lastName + "</a></td>\n\
                                <td><a href=\"addressbook/edit/" + data.id + "\">Edit</a></td>\n\
                                <td><a href=\"addressbook/delete/" + data.id + "\">Delete</a></td>\n\
                                                                                            \n\
                            </tr>";


        var strFooter = "<tr id=\"address-row-" + data.id + "\" >\n\
                                \n\
        <td>" + data.id + "</td>\n\
        <td><a data-address-id=\"" + data.id + "\" data-toggle=\"modal\" data-target=\"#showDetailModal\">" + data.firstName + "</a></td>\n\
        <td><a href=\"contact/show/" + data.id + "\">" + data.lastName + "</a></td>\n\
        <td><a data-address-id=\"" + data.id + "\" data-toggle=\"modal\" data-target=\"#editDetailModal\">Edit</a></td>\n\
        <td><a data-address-id=\"" + data.id + "\" class=\"delete-link\">Delete</a></td>\n\
                                                                                        \n\
        </tr>";

        return strFooter;

    }

    $('#showDetailModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var addressId = link.data('address-id');

        $.ajax({
            url: contextRoot + "/addressbook/" + addressId,
            type: "GET",
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {


                $('#address-first-name').text(data.firstName);

//                var adate = data.releaseDate;
//
//                adate = new Date(adate);

                $('#address-last-name').text(data.lastName);
                $('#address-street-name').text(data.streetName);
                $('#address-street-number').text(data.streetNumber);
                $('#address-city').text(data.city);
                $('#address-state').text(data.state);
                $('#address-zipcode').text(data.zip);

               // $('.edit-from-detail-button').data("address-id", data.id);

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

        var addressId = link.data('address-id');

          $.ajax({
            url: contextRoot + "/addressbook/" + addressId,
            type: "GET",
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json")
            },
            success: function (data, status) {
                $('#edit-address-first-name').val(data.firstName);
                $('#edit-address-last-name').val(data.lastName);
                $('#edit-address-street-name').val(data.streetName);
                $('#edit-address-street-number').val(data.streetNumber);
                $('#edit-address-city').val(data.city);
                $('#edit-address-state').val(data.state);
                $('#edit-address-zipcode').val(data.zip);
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

    $('#edit-address-button').on('click', function (e) {

        e.preventDefault();

        var addressData = JSON.stringify({
            id: $("#edit-id").val(),
            firstName: $("#edit-address-first-name").val(),
            lastName: $("#edit-address-last-name").val(),
            streetNumber: $("#edit-address-street-number").val(),
            streetName: $("#edit-address-street-name").val(),
            city: $("#edit-address-city").val(),
            state: $("#edit-address-state").val(),
            zip: $("#edit-address-zipcode").val()
        });


        $.ajax({
            url: contextRoot + "/addressbook/",
            type: "PUT",
            data: addressData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                $('#editDetailModal').modal('hide');

                var tableRow = buildDvdRow(data);

                $('#address-row-' + data.id).replaceWith($(tableRow));

            },
            error: function (data, status) {
                alert("error");
            }



        });

    });

    $(document).on('click', '.delete-link', function (e) {

        e.preventDefault();

        var addressId = $(e.target).data('address-id');

        $.ajax({
            type: "DELETE",
            url: contextRoot + "/addressbook/" + addressId,
            success: function (data, status) {
                $('#address-row-' + addressId).remove();
            },
            error: function (data, status) {

            }

        });


    });













});