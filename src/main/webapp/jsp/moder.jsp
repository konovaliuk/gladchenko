<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <link type="text/css" rel="stylesheet" href="css/main.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            <div class="col s4 offset-s2">
                <div>
                    <ul class="collapsible" data-collapsible="accordion">
                        <li>
                            <div class="collapsible-header active"><h4><c:out value="${event.getTopic()}" /></h4></div>
                            <div class="collapsible-body">
                            <span>
                            <h5>Place : <c:out value="${event.getPlace()}" /></h5>
                            <h5>Date : <fmt:formatDate value="${event.getCalendar().getTime()}" type="date"/></h5>
                            <h5>Time : <fmt:formatDate value="${event.getCalendar().getTime()}" type="time" timeStyle = "short"/></h5>
                            </span></div>
                        </li>
                      </ul>
                </div>
                <div>
                    <ul class="collapsible popout" data-collapsible="accordion">
                        <li>
                            <div class="collapsible-header"><i class="material-icons">autorenew</i>Change event</div>
                            <div class="collapsible-body">
                            <span>
                                <div>
                                    <form name="loginForm" method="POST" action="controller">
                                        <input type="hidden" name="command" value="eventchange"/>
                                        <select name="idevent" required>
                                            <option value="" disabled selected>Choose event</option>
                                            <c:forEach items="${eventlist}" var="el" >
                                                <option value="${el.getId()}"><c:out value="${el.getTopic()}" /></option>
                                            </c:forEach>
                                        </select><br>
                                        <br>
                                        <button class="btn waves-effect waves-light" type="submit" name="action">Change
                                            <i class="material-icons right">content_paste</i>
                                        </button>
                                    </form>
                                </div>
                            </span>
                            </div>
                        </li>
                        <li>
                        <div class="collapsible-header"><i class="material-icons">edit_location</i>Edit place & date</div>
                        <div class="collapsible-body">
                        <span>
                        <div>
                            <form name="loginForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="update"/>
                                <input type="hidden" name="id" value="${event.getId()}"/>
                                <input type="hidden" name="topic" value="${event.getTopic()}"/>
                                <div class="input-field">
                                    <label for="place">Place</label>
                                    <input id="place" type="text" class="validate" name="place" value="${event.getPlace()}" required>
                                </div>
                                <div class="input-field">
                                    <label for="date">Date</label>
                                    <input id="date" type="text" class="datepicker" name="date" required>
                                </div>
                                <div class="input-field">
                                    <label for="time">Time</label>
                                    <input id="time" type="text" class="timepicker" name="time" required>
                                </div>
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
                                <input type="hidden" name="idevent" value="${event.getId()}"/>
                                <select name="idreport" required>
                                    <option value="" disabled selected>Choose report</option>
                                    <c:forEach items="${list}" var="rp" >
                                        <option value="${rp.getId()}"><c:out value="${rp.getTopic()}" /></option>
                                    </c:forEach>
                                </select>
                                <div class="input-field">
                                    <label for="topic">Topic</label>
                                    <input id="topic" type="text" class="validate" name="topicreport" value="" required>
                                </div>
                                <div class="input-field">
                                    <label for="place">Place</label>
                                    <input id="place" type="text" class="validate" name="placereport" value="" required>
                                </div>
                                <div class="input-field">
                                    <label for="date">Date</label>
                                    <input id="date" type="text" class="datepicker" name="datereport" required>
                                </div>
                                <div class="input-field">
                                    <label for="time">Time</label>
                                    <input id="time" type="text" class="timepicker" name="timereport" required>
                                </div>
                                <select name="idspeaker" required>
                                    <option value="" disabled selected>Choose Speaker</option>
                                    <c:forEach items="${speakers}" var="sp" >
                                        <option value="${sp.getId()}"><c:out value="${sp.getLastName()}" /></option>
                                    </c:forEach>
                                </select>
                                <button class="btn waves-effect waves-light" type="submit" name="action">Update
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
                                <form name="editEventForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value="proptopicforspeaker"/>
                                    <div class="input-field">
                                        <label for="topicfs">Topic</label><br>
                                        <input id="topicfs" type="text" class="validate" name="topicforprop" required>
                                    </div>
                                    <select name="idspeaker" required>
                                        <option value="" disabled selected>Choose Speaker</option>
                                        <c:forEach items="${speakers}" var="s" >
                                            <option value="${s.getId()}"><c:out value="${s.getLastName()}" /></option>
                                        </c:forEach>
                                    </select>
                                    <br/>
                                    <button class="btn waves-effect waves-light" type="submit" name="action">Send topic
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
                                <ul id="dropdown2" class="dropdown-content">
                                    <c:forEach items="${conftopic}" var="ct" >
                                        <li><c:out value="${ct.getTopic()}" /> - Speaker: <c:out value="${ct.getIdSpeaker()}" /></li>
                                    </c:forEach>
                                </ul>
                                <a class="btn dropdown-button" href="#!" data-activates="dropdown2">Confirmed topics<i class="material-icons right">arrow_drop_down</i></a>
                            </div>
                            <div>
                                <form name="editEventForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="confspeakertopic"/>
                                <c:forEach items="${newspeakertopics}" var="nt" >
                                <p>
                                  <input class="with-gap" name="idconftopic" type="radio" id="${nt.getId()}" value="${nt.getId()}" checked/>
                                  <label for="${nt.getId()}"><c:out value="${nt.getTopic()}" /></label>
                                </p>
                                </c:forEach>
                                <br>
                                <select name="topicstatus" required>
                                    <option value="" disabled selected>Confirm or cancel</option>
                                    <option value="confirm">Confirm</option>
                                    <option value="cancel">Cancel</option>
                                </select>
                                <br>
                                <button class="btn waves-effect waves-light" type="submit" name="action">Send
                                <i class="material-icons left">send</i>
                                </button>
                                </form>
                            </div>
                        </span>
                        </div>
                        </li>
                        <li>
                            <div class="collapsible-header"><i class="material-icons">subject</i>Registration statistic</div>
                            <div class="collapsible-body">
                            <span>
                                <div>
                                    <h5>Registrations: <c:out value="${registrstat}" /> <i class="material-icons">person</i></h5>
                                    <br>
                                    <h5>Visitors: 10    <i class="material-icons">person</i></h5>
                                </div>
                            </span>
                            </div>
                        </li>
                        <li>
                        <div class="collapsible-header"><i class="material-icons">email</i>Send Emails</div>
                        <div class="collapsible-body">
                        <span>
                            <div>
                                <form name="editEventForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value="sendemail"/>
                                    <div class="input-field">
                                        <label for="topic">Topic</label>
                                        <input type="text" name="emailtopic" value="" required>
                                    </div>
                                    <div class="input-field">
                                        <label for="textarea1">Text</label>
                                        <textarea id="textarea1" class="materialize-textarea" name="emailtext"></textarea>
                                    </div>
                                    <br/>
                                    <br/>
                                    <button class="btn waves-effect waves-light" type="submit" name="action">Send Emails
                                    <i class="material-icons right">send</i>
                                    </button>
                                </form>
                            </div>
                        </span>
                        </div>
                    </li>
                    </ul>
                </div>
            </div>
            <div class="col s4 offset-s1">
                <div>
                    <h5>All reports:</h5>
                    <ul class="collection">
                        <c:forEach items="${list}" var="i" >
                            <li class="collection-item avatar">
                                <i class="material-icons circle">event_available</i>
                                <span class="title"><c:out value="${i.getTopic()}" /></span>
                                <p>Place: <c:out value="${i.getPlace()}" /><br>
                                    <fmt:formatDate value="${i.getCalendar().getTime()}" type="both" timeStyle = "short"/>
                                </p>
                                <a class="secondary-content"><i class="material-icons">grade</i></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <br>
                <div>
                    <h5><c:out value="${msg}" /></h5>
                </div>
                <div align="center">
                    <form name="loginForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="logout"/>
                        <br>
                        <button class="btn waves-effect waves-light" type="submit" name="action">Logout
                            <i class="material-icons left">exit_to_app</i>
                        </button>
                    </form>
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
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript" src="js/moder.js"></script>
        <script>
           $(document).ready(function() {
               $('select').material_select();
             });
       </script>
    </body>
</html>
