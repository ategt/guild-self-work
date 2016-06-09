/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function (d) {

    $('#create-sumbit').on('click', function (e) {

        //alert("Button Works"); 
        e.preventDefault();

        var contactData = JSON.stringify({
            firstName: $('#first-name-input').val(),
            lastName: $('#last-name-input').val(),
            company: $('#company-input').val(),
            email: $('#email-input').val(),
            phone: $('#phone-input').val()
        });

        $.ajax({
            url: contextRoot + "/contact/",
            type: "POST",
            data: contactData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                //alert("Succes");
                console.log(status);
                console.log(data);

                var tableRow = buildContactRow(data);

                $('#contact-table').append($(tableRow));

                resetCreateForm();

            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;
                var validationErrorMessage = "";
                
                resetCreateFormColors();
                
//                        $('#last-name-input-group').addClass("has-error");
//                        $('#first-name-input-group').addClass("has-error");

//
//                $('#first-name-input').val('');
//                $('#last-name-input').val('');
//                $('#company-input').val('');
//                $('#email-input').val('');
//                $('#phone-input').val('');
//                $('#last-contacted-input').val('');
//has-success
                $.each(errors, function (index, error) {

                    var errorFieldName = error.fieldName;
                    validationErrorMessage += errorFieldName + ":" + error.message + "<br />";
                    //$(error.fieldName).addClass("has-error");


                    if (errorFieldName === "firstName") {
                        $('#first-name-input-group').addClass("has-error");
                    } else {
                        $('#first-name-input-group').addClass("has-success");
                    }

                    if (errorFieldName === "lastName") {
                        $('#last-name-input-group').addClass("has-error");
                    } else {
                        $('#last-name-input-group').addClass("has-success");
                    }

                    if (errorFieldName === "company") {
                        $('#company-input-group').addClass("has-error");
                    } else {
                        $('#company-input-group').addClass("has-success");
                    }

                    if (errorFieldName === "email") {
                        $('#email-input-group').addClass("has-error");
                    } else {
                        $('#email-input-group').addClass("has-success");
                    }

                    if (errorFieldName === "phone") {
                        $('#phone-input-group').addClass("has-error");
                    } else {
                        $('#phone-input-group').addClass("has-success");
                    }

                    if (errorFieldName === "lastContacted") {
                        $('#lastContacted-input-group').addClass("has-error");
                    } else {
                        $('#lastContacted-input-group').addClass("has-success");
                    }




                });

                $("#add-contact-validation-errors").html(validationErrorMessage);






//                
//                $('#contact-first-name').text(data.firstName);
//                $('#contact-last-name').text(data.lastName);
//                $('#contact-company').text(data.company);
//                $('#contact-email').text(data.email);
//                $('#contact-phone').text(data.phone);
//                $('#contact-last-contacted').text(data.lastContacted);
            }


        });



    });


    $('#showContactModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var contactId = link.data('contact-id');

        $.ajax({
            url: contextRoot + "/contact/" + contactId,
            type: "GET",
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json")
            },
            success: function (data, status) {
                $('#contact-first-name').text(data.firstName);
                $('#contact-last-name').text(data.lastName);
                $('#contact-company').text(data.company);
                $('#contact-email').text(data.email);
                $('#contact-phone').text(data.phone);
                $('#contact-last-contacted').text(data.lastContacted);

            },
            error: function (data, status) {

            }

        });

    });




    $('#editContactModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var contactId = link.data('contact-id');

        $.ajax({
            url: contextRoot + "/contact/" + contactId,
            type: "GET",
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json")
            },
            success: function (data, status) {
                $('#edit-contact-first-name').val(data.firstName);
                $('#edit-contact-last-name').val(data.lastName);
                $('#edit-contact-company').val(data.company);
                $('#edit-contact-email').val(data.email);
                $('#edit-contact-phone').val(data.phone);
                $('#edit-id').val(data.id);
                var lastContacted = data.lastContacted;
                if (data.lastContacted == null) {
                    lastContacted = new Date();
                }

                $('#edit-contact-last-contacted').val(lastContacted.toLocaleDateString());

            },
            error: function (data, status) {

            }

        });

    });



    $('#edit-contact-button').on('click', function (e) {

        e.preventDefault();

        var contactData = JSON.stringify({
            firstName: $('#edit-contact-first-name').val(),
            lastName: $('#edit-contact-last-name').val(),
            company: $('#edit-contact-company').val(),
            email: $('#edit-contact-email').val(),
            id: $('#edit-id').val(),
            phone: $('#edit-contact-phone').val()
        });

        $.ajax({
            url: contextRoot + "/contact/",
            type: "PUT",
            data: contactData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                $('#editContactModal').modal('hide');

                var tableRow = buildContactRow(data);

                $('#contact-row-' + data.id).replaceWith($(tableRow));

            },
            error: function (data, status) {
                alert("error");
            }



        });

    });


    $(document).on('click', '.delete-link', function (e) {

        e.preventDefault();

        var contactId = $(e.target).data('contact-id');

        $.ajax({
            type: "DELETE",
            url: contextRoot + "/contact/" + contactId,
            success: function (data, status) {
                $('#contact-row-' + contactId).remove();
            },
            error: function (data, status) {

            }



        });


    });


    function buildContactRow(data) {

        var linkInfo = "data-contact-id=\"" + data.id + "\" data-toggle=\"modal\" data-target=\"#showContactModal\"";

        var strVar = "<tr>\n\
        <td>" + data.id + "</td>\n\
        <td><a " + linkInfo + ">" + data.firstName + "</a></td>\n\
        <td><a " + linkInfo + ">" + data.lastName + "</a></td>\n\
        <td><a data-contact-id=\"" + data.id + "\" data-toggle=\"modal\" data-target=\"#editContactModal\" >Edit</a></td>\n\
        <td><a href=\"contact/delete/" + data.id + "\">Delete</a></td>\n\
                \n\
    </tr>";

        //alert(strVar);

//        <td><a href=\"contact/show/" + data.id + "\">" + data.firstName + "</a></td>\n\
        return strVar;

    }

    function resetCreateForm() {

        $("#add-contact-validation-errors").html();

        $('#first-name-input').val('');
        $('#last-name-input').val('');
        $('#company-input').val('');
        $('#email-input').val('');
        $('#phone-input').val('');
        $('#last-contacted-input').val('');

        resetCreateFormColors();
    }

    function resetCreateFormColors() {


        $('#first-name-input-group').removeClass("has-error");
        $('#first-name-input-group').removeClass("has-success");

        $('#last-name-input-group').removeClass("has-error");
        $('#last-name-input-group').removeClass("has-success");

        $('#company-input-group').removeClass("has-error");
        $('#company-input-group').removeClass("has-success");

        $('#email-input-group').removeClass("has-error");
        $('#email-input-group').removeClass("has-success");

        $('#phone-input-group').removeClass("has-error");
        $('#phone-input-group').removeClass("has-success");

        $('#last-contacted-input-group').removeClass("has-error");
        $('#last-contacted-input-group').removeClass("has-success");
    }

});