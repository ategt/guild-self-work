<html>
    <head>
            <!--The Styles that came with dropfile.-->
        <link href="${pageContext.request.contextPath}/css/dropfile.css" rel="stylesheet">

    </head>

    <body>
        <div id="dragandrophandler">Drag & Drop Files Here</div>
        <br><br>
        <div id="status1"></div>

        <script>
            var contextRoot = "${pageContext.request.contextPath}";
        </script>

        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dropfile.js"></script>
    </body>

</html>