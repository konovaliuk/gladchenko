<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error</title>
    </head>
    <body>
        <nav class="cyan darken-4">
            <div class="nav-wrapper">
                <a href="/conference" class="brand-logo center">Conference<i class="material-icons">event</i></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">

                </ul>
            </div>
        </nav>
        <div class="row">
            <div class="col s6 offset-s3" align="center">
                <br>
                <h5>Error</h5>
                <c:out value="${errormessage}"/>
            </div>
        </div>

    </body>
</html>