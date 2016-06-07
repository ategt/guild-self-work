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


        return strTableRow;

    }

});