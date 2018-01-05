<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Conference</title>
    </head>
    <body>
        <!--Import jQuery before materialize.js-->
          <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
          <script type="text/javascript" src="js/materialize.min.js"></script>

        <nav class="cyan darken-4">
            <div class="nav-wrapper">
                <a href="/conference" class="brand-logo center">Conference<i class="material-icons">event</i></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a id="target">All Events</a></li>
                    <li><a href="#">Account</a></li>
                </ul>
            </div>
        </nav>
        <br>
        <div class="row">
            <div class="col s5 offset-s1">
                <div>
                    <h5>All reports:</h5>
                    <br>
                    <ul class="collection">
                        <c:forEach items="${list}" var="i" >
                            <li class="collection-item avatar">
                                <i class="material-icons circle">event_available</i>
                                <span class="title"><c:out value="${i.getTopic()}" /></span>
                                <p>Place: <c:out value="${i.getPlace()}" /><br>
                                    <fmt:formatDate value="${i.getCalendar().getTime()}" type="time" timeStyle = "short"/>
                                </p>
                                <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a>
                            </li>
                        </c:forEach>
                    </ul>
                    <br>
                </div>
                <div>
                    <form name="loginForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="eventchange"/>
                        <select class="browser-default" name="idevent" required>
                            <option value="" disabled selected>Choose report</option>
                            <c:forEach items="${eventlist}" var="el" >
                                <option value="${el.getId()}"><c:out value="${el.getTopic()}" /></option>
                            </c:forEach>
                        </select><br>
                        <br>
                        <button class="btn waves-effect waves-light btn-large" type="submit" name="action">Change event
                            <i class="material-icons right">content_paste</i>
                        </button>
                    </form>
                </div>
            </div>
            <div class="col s4 offset-s1">
                <div class="card">
                    <div class="card-content">
                        <h4><c:out value="${event.getTopic()}" /></h4>
                        <h5>Place : <c:out value="${event.getPlace()}" /></h5>
                        <h5>Date : <fmt:formatDate value="${event.getCalendar().getTime()}" type="date"/></h5>
                        <h5>Time : <fmt:formatDate value="${event.getCalendar().getTime()}" type="time" timeStyle = "short"/></h5>
                    </div>
                </div>
               <br>
               <br>
               <br>
               <br>
                <form name="loginForm" method="POST" action="controller">
                    <input type="hidden" name="command" value="reportregistr"/>
                    <input type="hidden" name="userid" value="${userid}"/>
                    <input type="hidden" name="eventid" value="${event.getId()}"/>
                    <select class="browser-default" name="reportid" required>
                        <option value="" disabled selected>Choose report</option>
                        <c:forEach items="${list}" var="rp" >
                            <option value="${rp.getId()}"><c:out value="${rp.getTopic()}" /></option>
                        </c:forEach>
                    </select><br>
                    <br>
                    <button class="btn waves-effect waves-light btn-large" type="submit" name="action">Registration
                        <i class="material-icons left">send</i>
                    </button>
                </form>
                <br>
                <br>
                <br>
                <h5><c:out value="${succesmsg}" /></h5>
            </div>
        </div>
        <footer class="cyan darken-4">
            <div class="container">
                <div class="row">
                    <div class="col l6 s12">
                        <h5 class="white-text">Footer Content</h5>
                        <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer content.</p>
                    </div>
                    <div class="col l4 offset-l2 s12">
                        <h5 class="white-text">Links</h5>
                        <ul>
                            <li><a class="grey-text text-lighten-3" href="#!">Link 1</a></li>
                            <li><a id="target" class="grey-text text-lighten-3">Link 2</a></li>
                            <li><a class="grey-text text-lighten-3" href="#!">Link 3</a></li>
                            <li><a class="grey-text text-lighten-3" href="#!">Link 4</a></li>
                        </ul>
                    </div>
                </div>
                </div>
                <div class="footer-copyright">
                <div class="container">
                © 2017 Copyright Text
                <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
                </div>
            </div>
        </footer>
        <script>
           $( "#target" ).click(function() {
             var commandObj = {
                 "command": "pagination",
                 "page" : "2"
             }

             var url = "http://localhost:8080/conference/controller";

             $.ajax({
                 url: url,
                 method: "post",
                 data: commandObj,
                 error: function(message) {
                     console.log(message);
                 },
                 success: function() {
                    alert("Успешное выполнение");;
                 }
             });
           });
       </script>
    </body>
</html>