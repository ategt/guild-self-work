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


        var strFooter = "<tr id=\"contact-row-" + data.id + "\" >\n\
                                \n\
        <td>" + data.id + "</td>\n\
        <td><a data-contact-id=\"" + data.id + "\" data-toggle=\"modal\" data-target=\"#showContactModal\">" + data.firstName + "</a></td>\n\
        <td><a href=\"contact/show/" + data.id + "\">" + data.lastName + "</a></td>\n\
        <td><a data-contact-id=\"" + data.id + "\" data-toggle=\"modal\" data-target=\"#editContactModal\">Edit</a></td>\n\
        <td><a data-contact-id=\"" + data.id + "\" class=\"delete-link\">Delete</a></td>\n\
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

        var link = $(e.relatedTarget);

        var addressId = link.data('address-id');

        pullUpEditMenu(addressId);

    });

    $('#edit-address-button').on('click', function (e) {

        e.preventDefault();

        var addressData = JSON.stringify({
            id: $("#edit-id").val(),
            title: $("#edit-address-title").val(),
            releaseDate: $("#edit-address-release-date").val(),
            rating: $("#edit-address-rating").val(),
            directorsName: $("#edit-address-director").val(),
            studio: $("#edit-address-studio").val()
        });


        $.ajax({
            url: contextRoot + "/addresslibrary/",
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
            url: contextRoot + "/addresslibrary/" + addressId,
            success: function (data, status) {
                $('#address-row-' + addressId).remove();
            },
            error: function (data, status) {

            }

        });


    });













});