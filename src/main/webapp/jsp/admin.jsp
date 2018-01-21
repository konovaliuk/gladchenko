<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="rs" uri="myTLD"%>
<html>
    <head>
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <link type="text/css" rel="stylesheet" href="css/main.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conference</title>
    </head>
    <body>
        <nav class="cyan darken-4">
            <div class="nav-wrapper">
                <a href="/conference" class="brand-logo center">Conference<i class="material-icons">event</i></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li></li>
                </ul>
            </div>
        </nav>
        <br>
        <div>
            <ul id="slide-out" class="side-nav">
                <li><div class="user-view">
                  <div class="background">
                    <img src="images/image.jpg">
                  </div>
                  <a href="#!user"><img class="circle" src="images/no_image.jpg"></a>
                  <a href="#!name"><span class="white-text name">John Dou</span></a>
                  <a href="#!email"><span class="white-text email">johndou@gmail.com</span></a>
                </div></li>
                <br>
                <li><a href="/conference/topics.jsp">${varAllTopics}</a></li>
                    <li>
                        <div align="center">
                            <form name="loginForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="logout"/>
                                <br>
                                <button class="btn waves-effect waves-light" type="submit" name="action">${varLogoutBtn}
                                    <i class="material-icons left">exit_to_app</i>
                                </button>
                            </form>
                        </div>
                    </li>
                    <li><div class="divider"></div></li>
                    <li><a class="subheader">Change language</a></li>
                    <li>
                        <div>
                            <form name="loginForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="changelang"/>
                                <input class="with-gap" name="lang" type="radio" id="1" value="EN" checked/>
                                <label for="1">English</label> |
                                <input class="with-gap" name="lang" type="radio" id="2" value="DE" checked/>
                                <label for="2">Deutsch</label> |
                                <input class="with-gap" name="lang" type="radio" id="3" value="RU" checked/>
                                <label for="3">Русский</label>
                                <div align="center">
                                    <button class="btn waves-effect waves-light" type="submit" name="action">${varChangeLangBtn}
                                       <i class="material-icons left">autorenew</i>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </li>
              </ul>
        </div>
        <div class="row">
            <div class="col s4 offset-s2">
                <div>
                    <ul class="collapsible" data-collapsible="accordion">
                        <li>
                            <div class="collapsible-header active"><h4><c:out value="${event.getTopic()}" /></h4></div>
                            <div class="collapsible-body">
                            <span>
                            <h5>${varPlace} : <c:out value="${event.getPlace()}" /></h5>
                            <h5>${varDate} : <fmt:formatDate value="${event.getCalendar().getTime()}" type="date"/></h5>
                            <h5>${varTime} : <fmt:formatDate value="${event.getCalendar().getTime()}" type="time" timeStyle = "short"/></h5>
                            </span></div>
                        </li>

                      </ul>
                </div>
                <br>
                <div>
                <ul class="collapsible popout" data-collapsible="accordion">
                <li>
                <div class="collapsible-header"><i class="material-icons">autorenew</i>${varChangeEvent}</div>
                <div class="collapsible-body">
                <span>
                     <div>
                        <form name="loginForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="eventchange"/>
                            <select name="idevent" required>
                                <option value="" disabled selected>${varChooseEvent}</option>
                                <c:forEach items="${eventlist}" var="el" >
                                    <option value="${el.getId()}"><c:out value="${el.getTopic()}" /></option>
                                </c:forEach>
                            </select>
                            <button class="btn waves-effect waves-light" type="submit" name="action">${varChangeEventBtn}
                                <i class="material-icons right">content_paste</i>
                            </button>
                        </form>
                    </div>
                </span>
                </div>
                </li>
                <li>
                <div class="collapsible-header"><i class="material-icons">event_note</i>Add Event</div>
                <div class="collapsible-body">
                <span>
                    <div>
                        <form name="loginForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="addevent"/>
                            <div class="input-field">
                                <label for="topic">${varTopic}</label>
                                <input id="topic" type="text" class="validate" name="eventTopic" value="" required>
                            </div>
                            <div class="input-field">
                                <label for="place">${varPlace}</label>
                                <input id="place" type="text" class="validate" name="eventPlace" value="" required>
                            </div>
                            <div class="input-field">
                                <label for="date">${varDate}</label>
                                <input id="date" type="text" class="datepicker" name="eventDate" required>
                            </div>
                            <div class="input-field">
                                <label for="time">${varTime}</label>
                                <input id="time" type="text" class="timepicker" name="eventTime" required>
                            </div>
                            <br>
                            <button class="btn waves-effect waves-light" type="submit" name="action">Add
                                <i class="material-icons left">send</i>
                            </button>
                        </form>
                    </div>
                </span>
                </div>
                </li>
                </ul>
                </div>
                <br>
                    <div align="center">
                        <a href="#" data-activates="slide-out" class="button-collapse"><i class="medium material-icons">menu</i></a>
                    </div>
                <br>

            </div>
            <div class="col s4 offset-s1">
                <div>
                    <h5>${varAllReports} </h5>
                    <ul class="collection">
                        <c:forEach items="${list}" var="i" >
                            <li class="collection-item avatar">
                                <i class="material-icons circle">event_available</i>
                                <span class="title"><c:out value="${i.getTopic()}" /></span>
                                <p>${varPlace}: <c:out value="${i.getPlace()}" /><br>
                                    <fmt:formatDate value="${i.getCalendar().getTime()}" type="both" timeStyle = "short"/>
                                </p>
                                <p>${varSpeaker}: ${i.getSpeakerLastName()}</p>
                                <a id="menu" class="secondary-content"><i class="material-icons">grade</i></a>

                            </li>
                        </c:forEach>
                    </ul>
                </div>

                <br>
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
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript" src="js/moder.js"></script>
        <script>
           $(document).ready(function() {
               $('select').material_select();
             });
             $(".button-collapse").sideNav();
       </script>
    </body>
</html>
