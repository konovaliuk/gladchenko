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
                    <li><a href="/conference/topics.jsp">All topics</a></li>
                </ul>
            </div>
        </nav>
        <br>
        <div class="row">
            <div class="col s5 offset-s1">
                <h5>All reports:</h5>
                <ul class="collection">
                    <c:forEach items="${list}" var="i" >
                        <li class="collection-item avatar">
                            <i class="material-icons circle">event_available</i>
                            <span class="title"><c:out value="${i.getTopic()}" /></span>
                            <p>Place: <c:out value="${i.getPlace()}" /><br>
                                <fmt:formatDate value="${i.getCalendar().getTime()}" type="both" timeStyle = "short"/>
                            </p>
                            <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <br>
            <div class="col s4 offset-s1">
                <br>
                <div class="card">
                    <div class="card-content">
                        <h4><c:out value="${event.getTopic()}" /></h4>
                        <h5>Place : <c:out value="${event.getPlace()}" /></h5>
                        <h5>Date : <fmt:formatDate value="${event.getCalendar().getTime()}" type="date"/></h5>
                        <h5>Time : <fmt:formatDate value="${event.getCalendar().getTime()}" type="time" timeStyle = "short"/></h5>
                    </div>
                </div>
                <br>
                <div>
                    <ul class="collapsible" data-collapsible="accordion">
                        <li>
                        <div class="collapsible-header"><i class="material-icons">edit_location</i>Edit place & date</div>
                        <div class="collapsible-body">
                        <span>
                        <div>
                            <form name="loginForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="update"/>
                                <input type="hidden" name="id" value="1"/>
                                <input type="hidden" name="topic" value="${event.getTopic()}"/>
                                Place:<br/>
                                <input type="text" name="place" value="" required>
                                Date:<br/>
                                <input type="text" class="datepicker" name="date" required>
                                Time:<br/>
                                <input type="text" class="timepicker" name="time" required>
                                <br>
                                <button class="btn waves-effect waves-light" type="submit" name="action">Update
                                    <i class="material-icons left">update</i>
                                </button>
                            </form>
                        </div>
                        </span>
                        </div>
                        </li>
                        <li>
                        <div class="collapsible-header"><i class="material-icons">edit</i>Edit report</div>
                        <div class="collapsible-body">
                        <span>
                        <div>
                            <form name="loginForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="updatereport"/>
                                <input type="hidden" name="idevent" value="1"/>
                                <select class="browser-default" name="idreport" required>
                                    <option value="" disabled selected>Choose report</option>
                                    <c:forEach items="${list}" var="rp" >
                                        <option value="${rp.getId()}"><c:out value="${rp.getTopic()}" /></option>
                                    </c:forEach>
                                </select><br>
                                Topic:<br/>
                                <input type="text" name="topicreport" value="" required>
                                Place:<br/>
                                <input type="text" name="placereport" value="" required>
                                Date:<br/>
                                <input type="text" class="datepicker" name="datereport" required>
                                Time:<br/>
                                <input type="text" class="timepicker" name="timereport" required>
                                <select class="browser-default" name="idspeaker" required>
                                    <option value="" disabled selected>Choose Speaker</option>
                                    <c:forEach items="${speakers}" var="sp" >
                                        <option value="${sp.getId()}"><c:out value="${sp.getLastName()}" /></option>
                                    </c:forEach>
                                </select>
                                <br>
                                <button class="btn waves-effect waves-light btn-large" type="submit" name="action">Update
                                    <i class="material-icons left">update</i>
                                </button>
                            </form>
                            <br>
                        </div>
                        </span>
                        </div>
                        </li>
                        <li>
                        <div class="collapsible-header"><i class="material-icons">message</i>Propose topic for speaker</div>
                        <div class="collapsible-body">
                        <span>
                            <div>
                                <h5>Propose topic for speaker:</h5>
                                <form name="editEventForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value="proptopicforspeaker"/>
                                    <i class="material-icons prefix">mode_edit</i>
                                    <label for="icon_prefix2">Topic:</label><br>
                                    <input type="text"  name="topicforprop" required>
                                    <br/>
                                    <br/>
                                    <select class="browser-default" name="idspeaker" required>
                                        <option value="" disabled selected>Choose Speaker</option>
                                        <c:forEach items="${speakers}" var="s" >
                                            <option value="${s.getId()}"><c:out value="${s.getLastName()}" /></option>
                                        </c:forEach>
                                    </select>
                                    <br/>
                                    <button class="btn waves-effect waves-light btn-large" type="submit" name="action">Send topic
                                    <i class="material-icons right">send</i>
                                    </button>
                                </form>
                                <br>
                            </div>
                        </span>
                        </div>
                        </li>
                        <li>
                        <div class="collapsible-header"><i class="material-icons">check</i>Confirmed topics</div>
                        <div class="collapsible-body">
                        <span>
                            <div>
                                <ul>
                                    <c:forEach items="${conftopic}" var="ct" >
                                        <li><c:out value="${ct.getTopic()}" /> - Speaker: <c:out value="${ct.getIdSpeaker()}" /></li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </span>
                        </div>
                        </li>
                      </ul>
                </div>
                <div>
                    <h5><c:out value="${msg}" /></h5>
                </div>
            </div>
            <br>
            <br>
        </div>
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
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
        <script>
            $('.datepicker').pickadate({
                selectMonths: true, // Creates a dropdown to control month
                selectYears: 15, // Creates a dropdown of 15 years to control year,
                format: 'yyyy-mm-dd',
                today: 'Today',
                clear: 'Clear',
                close: 'Ok',
                closeOnSelect: false // Close upon selecting a date,
              });

            $('.timepicker').pickatime({
                default: 'now', // Set default time: 'now', '1:30AM', '16:30'
                fromnow: 0,       // set default time to * milliseconds from now (using with default = 'now')
                twelvehour: false, // Use AM/PM or 24-hour format
                donetext: 'OK', // text for done-button
                cleartext: 'Clear', // text for clear-button
                canceltext: 'Cancel', // Text for cancel-button
                autoclose: false, // automatic close timepicker
                ampmclickable: true, // make AM PM clickable
                aftershow: function(){} //Function for after opening timepicker
              });

            $(document).ready(function(){
                $('.collapsible').collapsible();
              });
        </script>
    </body>
</html>
