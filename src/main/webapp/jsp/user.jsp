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
        <link type="text/css" rel="stylesheet" href="css/user.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conference</title>
    </head>
    <body>
	<header>
        <nav class="cyan darken-4">
            <div class="nav-wrapper">
                <a href="/conference" class="brand-logo center">Conference<i class="material-icons">event</i></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li></li>
                </ul>
            </div>
        </nav>
	</header>
	<main>
        <br>
        <div>
            <ul id="slide-out" class="side-nav fixed">
            <li>
            <div class="user-view">
                <h5>${varPlace} : <c:out value="${event.getPlace()}" /></h5>
                <h5>${varDate} : <fmt:formatDate value="${event.getCalendar().getTime()}" type="date"/></h5>
                <h5>${varTime} : <fmt:formatDate value="${event.getCalendar().getTime()}" type="time" timeStyle = "short"/></h5>
            </div>
            </li>
            <br>
            <br>
            <li><div class="divider"></div></li>
            <br>
            <li>
            <div>
                <ul class="collapsible" data-collapsible="accordion">
                    <li>
                        <div class="collapsible-header"><i class="material-icons">autorenew</i>${varChangeEvent}</div>
                        <div class="collapsible-body">
                        <span>
                             <div align="center">
                                <form name="loginForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value="eventchange"/>
                                    <select name="idevent" required>
                                        <option value="" disabled selected>${varChooseEvent}</option>
                                        <c:forEach items="${eventlist}" var="el" >
                                            <option value="${el.getId()}"><c:out value="${el.getTopic()}" /></option>
                                        </c:forEach>
                                    </select>
                                    <br>
                                    <button class="btn waves-effect waves-light" type="submit" name="action">${varChangeEventBtn}
                                        <i class="material-icons right">content_paste</i>
                                    </button>
                                </form>
                            </div>
                        </span>
                        </div>
                    </li>
                    <li>
                    <div class="collapsible-header"><i class="material-icons">event_note</i>${varChooseReport}</div>
                    <div class="collapsible-body">
                    <span>
                        <div align="center">
                            <form name="loginForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="reportregistr"/>
                                <input type="hidden" name="userid" value="${userid}"/>
                                <input type="hidden" name="eventid" value="${event.getId()}"/>
                                <select name="reportid" required>
                                    <option value="" disabled selected>${varChangeReport}</option>
                                    <c:forEach items="${list}" var="rp" >
                                        <option value="${rp.getId()}"><c:out value="${rp.getTopic()}" /></option>
                                    </c:forEach>
                                </select>
                                <br>
                                <button class="btn waves-effect waves-light" type="submit" name="action">${varRegistrationBtn}
                                    <i class="material-icons left">send</i>
                                </button>
                            </form>
                        </div>
                    </span>
                    </div>
                    </li>
                </div>
                <br>
            </li>
		    <li><div class="divider"></div></li>
		    <br>
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
            <li>
            <br>
            <div align="center">
                <c:out value="${succesmsg}" />
            </div>
            </li>
        </ul>
        </div>
	    <div class="row">
            <div class="col s7 offset-s2">
                <div id="event" class="section scrollspy" align="center">
                    <h4><c:out value="${event.getTopic()}" /></h4>
                </div>
                <br>
                <h5>${varAllReports}</h5>
                <div id="reports" class="section scrollspy">
                    <ul class="collection">
                        <c:forEach items="${list}" var="i" >
                            <li class="collection-item avatar">
                                <i class="material-icons circle">event_available</i>
                                <span class="title"><p class="flow-text"><c:out value="${i.getTopic()}" /></p></span>
                                <p>${varSpeaker}: ${i.getSpeakerLastName()}</p>
                                <p>${varPlace}: <c:out value="${i.getPlace()}" /><br>
                                    <fmt:formatDate value="${i.getCalendar().getTime()}" type="time" timeStyle = "short"/>
                                </p>
                                <a class="secondary-content"><i class="material-icons">grade</i></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div>
                    <br>
                    <div id="language" class="section scrollspy" align="center">
                        <form name="loginForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="changelang"/>
                        <input class="with-gap" name="lang" type="radio" id="1" value="EN" checked/>
                        <label for="1">English</label> |
                        <input class="with-gap" name="lang" type="radio" id="2" value="DE" checked/>
                        <label for="2">Deutsch</label> |
                        <input class="with-gap" name="lang" type="radio" id="3" value="RU" checked/>
                        <label for="3">Русский</label>
                        <div align="center">
                        <br>
                            <button class="btn waves-effect waves-light" type="submit" name="action">${varChangeLangBtn}
                               <i class="material-icons left">autorenew</i>
                            </button>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
            <br><br><br><br><br><br><br>
            <br><br><br><br><br><br><br>
            <br><br><br><br><br><br><br>
            <div class="col s2">
                <div class="col hide-on-small-only m3 l2">
                    <ul class="section table-of-contents">
                    <li><a href="#event">Event</a></li>
                    <li><a href="#reports">Reports</a></li>
                    <li><a href="#language">Language</a></li>
                    </ul>
                </div>
            </div>
        </div>

	</main>
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
            <br>
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
             $(document).ready(function(){
                 $('.scrollspy').scrollSpy();
               });
       </script>
    </body>
</html>
