/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $('#submit-button').on('click', function (e) {

        e.preventDefault();

        var dvdData = JSON.stringify({
            title: $("#title").val(),
            releaseDate: $("#releaseDate").val(),
            rating: $("#rating").val(),
            directorsName: $("#directorsName").val(),
            studio: $("#studio").val()
        });

        $.ajax({
            url: contextRoot + "/dvdlibrary/",
            type: "POST",
            data: dvdData,
            dataType: 'json',
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            
            success: function (data, status) {

                var tableRow = buildContactRow(data);

                $('#dvd-table').append($(tableRow));

            },
            error: function (data, status) {
                alert("error");
            }


        });


    });

    function buildContactRow(data) {


        var strTableRow = "<tr>\n\
        <td><a href=\"dvdlibrary/show/" + data.id + "\">" + data.title + "</a></td>\n\
        <td><a href=\"dvdlibrary/show/" + data.id + "\">" + data.studio + "</a></td>\n\
        <td><a href=\"dvdlibrary/edit/" + data.id + "\">Edit</a></td>\n\
        <td><a href=\"dvdlibrary/delete/" + data.id + "\">Delete</a></td>\n\
                                                                \n\
            </tr>";


        return strTableRow;

    }

});