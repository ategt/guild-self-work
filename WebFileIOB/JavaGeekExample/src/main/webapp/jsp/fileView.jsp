<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

    <body> 

        <h2>Spring MVC - Uploading a file.. </h2>
        <form:form method="POST" commandName="file"	enctype="multipart/form-data">

            Upload your file please: 
            <input type="file" name="file" />
            <input type="submit" value="upload" />
            <form:errors path="file" cssStyle="color: #ff0000;" />

            <fieldset id="zone">
                <legend>Drop a file inside...</legend>
                <p>Or click here to <em>Browse</em>...</p>
            </fieldset>

        </form:form>



        <script>

            // We can deal with iframe uploads using this URL:
            var options = {iframe: {url: ''}};
// 'zone' is an ID but you can also give a DOM node:
            var zone = new FileDrop('zone', options);

// Do something when a user chooses or drops a file:
            zone.event('send', function (files) {
// FileList might contain multiple items.
                files.each(function (file) {


                    file.event('done', function (xhr) {
                        // Here, 'this' points to fd.File instance.
                        alert(xhr.responseText);
                    });

// Send the file:
                    file.sendTo('file.htm');
                });
            });


            zone.event('iframeDone', function (xhr) {
                alert(xhr.responseText);
            });


        </script>

        
          <script src="${pageContext.request.contextPath}/js/filedrop.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>