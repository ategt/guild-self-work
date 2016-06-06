/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $('#create-submit').on('click', function (e) {

        e.preventDefault();
        console.log(contextRoot);
        var postableUrl = contextRoot + "/FlooringMaster/";
        console.log(postableUrl);

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

                var tableRow = buildContactRow(data);

                $('#order-table').append($(tableRow));

            },
            error: function (data, status) {
                alert("error");
            }


        });


    });

    function buildContactRow(data) {


        var strTableRow = "  <tr>\n\
                                <td><a href=\"/FlooringMaster/show/" + data.id + "\">" + data.id + "</a></td>\n\
                                <td><a href=\"/FlooringMaster/show/" + data.id + "\">" + data.name + "</a></td>\n\
                                <td><a href=\"/FlooringMaster/edit/" + data.id + "\">Edit</a></td>\n\
                                <td><a href=\"/FlooringMaster/delete/" + data.id + "\">Delete</a></td>\n\
\n\
                            </tr>";


        return strTableRow;

    }

});