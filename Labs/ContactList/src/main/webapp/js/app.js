/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(d) {

    $('#create-sumbit').on('click', function(e) {
        
       //alert("Button Works"); 
        e.preventDefault();
        
        var contactData = JSON.stringify({
          firstName:$('#first-name-input').val(),
          lastName:$('#last-name-input').val(),
          company:$('#company-input').val(),
          email:$('#email-input').val(),
          phone:$('#phone-input').val()
        });
        
        $.ajax({
         
            url: contextRoot + "/contact/",
            type: "POST",
            data: contactData,
            dataType: 'json',
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            
            success: function(data, status){
                //alert("Succes");
                console.log(status);
                console.log(data);
                
                var tableRow = buildContactRow(data);
                
                $('#contact-table').append( $(tableRow));
                
            },
            error: function(data, status){
                alert("error");
            }
            
            
        });
        
        
        alert("After Ajax");
        
        
    });
        
    function buildContactRow(data) {
    
        
   var strVar = "<tr>\n\
        <td><a href=\"contact/show/" + data.id + "\">" + data.firstName + "</a></td>\n\
        <td><a href=\"contact/show/" + data.id + "\">" + data.lastName + "</a></td>\n\
        <td><a href=\"contact/edit/" + data.id + "\">Edit</a></td>\n\
        <td><a href=\"contact/delete/" + data.id + "\">Delete</a></td>\n\
                \n\
    </tr>";
        
    //}
    
    var oldVersion = "<tr id='contact-row-" + data.id + "'>  \n\
                <td><a data-contact-id='" + data.id +"' data-toggle='modal' data-target='#showContactModal'>" + data.firstName + " " + data.lastName  + "</a></td>  \n\
                <td> " + data.company + "</td>    \n\
                <td> <a data-contact-id='" + data.id +"' data-toggle='modal' data-target='#editContactModal'>Edit</a>  </td>   \n\
                <td> <a data-contact-id='" + data.id +"' class='delete-link'>Delete</a>  </td>   \n\
                </tr>  ";
    
        return strVar;
    
    }
});