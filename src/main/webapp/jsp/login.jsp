<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <link type="text/css" rel="stylesheet" href="css/main.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
    </head>
    <body>
        <nav class="cyan darken-4">
            <div class="nav-wrapper lighten-2">
                <a href="/conference" class="brand-logo center">Conference<i class="material-icons">event</i></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="#">Registration</a></li>
                </ul>
            </div>
        </nav>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <div class="row">
            <div class="col s6 offset-s3" align="center">
                <form name="loginForm" method="POST" action="controller">
                    <input type="hidden" name="command" value="login" required/>
                    <div class="input-field">
                        <label for="login">Login</label>
                        <input id="login" type="text" class="validate" name="login" value="" required>
                    </div>
                    <div class="input-field">
                       <label for="password">Password</label>
                       <input id="password" type="password" class="validate" name="password" value="" required>
                    </div>
                    <br/>
                    <br/>
                    <br/>
                    <button class="btn waves-effect waves-light btn-large" type="submit" name="action">Submit
                        <i class="material-icons right">send</i>
                    </button>
                </form>
            </div>
        </div>
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script>
            $(document).ready(function() {
               $('select').material_select();
             });
       </script>
    </body>
</html>