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
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <meta charset="UTF-8">
        <title>Conference</title>
    </head>
    <body>
        <nav class="cyan darken-4">
            <div class="nav-wrapper">
                <a href="/conference" class="brand-logo center">Conference<i class="material-icons">event</i></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="#">All Events</a></li>
                </ul>
            </div>
        </nav>
        <br>
        <div class="row">
            <div class="col s5 offset-s1">
                <ul class="collection">
                    <c:forEach items="${list}" var="i" >
                        <li class="collection-item avatar">
                            <i class="material-icons circle">event_available</i>
                            <span class="title"><c:out value="${i.getTopic()}" /></span>
                            <p>Place: <c:out value="${i.getPlace()}" /> Id: <c:out value="${i.getId()}" /><br>
                                <fmt:formatDate value="${i.getCalendar().getTime()}" type="date"/>
                            </p>
                            <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col s4 offset-s1">
                <form name="loginForm" method="POST" action="controller">
                    <input type="hidden" name="command" value="update"/>
                    Id Event:<br/>
                    <input type="text" name="id" value="" required><br/>
                    Topic:<br/>
                    <input type="text" name="topic" value="" required><br/>
                    Place:<br/>
                    <input type="text" name="place" value="" required>
                    Date:<br/>
                    <input type="date" name="date" required>
                    Time:<br/>
                    <input type="time" name="time" required>
                    <select class="browser-default" required>
                        <option value="" disabled selected>Choose Speaker</option>
                        <option value="1">Ivanov</option>
                        <option value="2">Petrov</option>
                        <option value="3">Sidorov</option>
                        <option value="4">Azirov</option>
                        <option value="5">Klichko</option>
                    </select>
                    <br>
                    <button class="btn waves-effect waves-light btn-large" type="submit" name="action">Update
                        <i class="material-icons left">update</i>
                    </button>
                </form>
                <br>
            </div>
        </div>
        <div class="row">
            <div class="col s4 offset-s2">
                <ul class="pagination">
                    <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
                    <li class="active"><a href="#!">1</a></li>
                    <li class="waves-effect"><a href="#!">2</a></li>
                    <li class="waves-effect"><a href="#!">3</a></li>
                    <li class="waves-effect"><a href="#!">4</a></li>
                    <li class="waves-effect"><a href="#!">5</a></li>
                    <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
                </ul>
            </div>
        </div>

        <br>
        <br>
        <br>
        <br>
        <br>
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
                            <li><a class="grey-text text-lighten-3" href="#!">Link 2</a></li>
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
    </body>
</html>
