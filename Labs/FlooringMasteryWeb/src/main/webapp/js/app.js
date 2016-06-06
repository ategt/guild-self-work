/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $('#submit-button').on('click', function (e) {

        e.preventDefault();

        var addressData = JSON.stringify({
            name: $("#name").val(),
            state: $("#state").val(),
            product: $("#product").val(),
            date: $("#datepicker").val(),
            area: $("#area").val()
        });

        $.ajax({
            url: contextRoot + "/FlooringMaster/",
            type: "POST",
            data: addressData,
            dataType: 'json',
            beforeSend: function(xhr){
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
                                <td><a href="${pageContext.request.contextPath}/FlooringMaster/show/${order.id}">${order.id}</a></td>\n\
                                <td><a href="${pageContext.request.contextPath}/FlooringMaster/show/${order.id}">${order.name}</a></td>
                                <td><a href="${pageContext.request.contextPath}/FlooringMaster/edit/${order.id}">Edit</a></td>
                                <td><a href="${pageContext.request.contextPath}/FlooringMaster/delete/${order.id}">Delete</a></td>

                            </tr>";


        return strTableRow;

    }

});