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
        <header>
            <nav class="cyan darken-4">
                <div class="nav-wrapper">
                    <a href="/conference" class="brand-logo center">Conference<i class="material-icons">event</i></a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down">
                        <li><a href="/conference/topics.jsp">${varAllTopics}</a></li>
                    </ul>
                </div>
            </nav>
        </header>
        <main>
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
        <a href="#!role"><span class="white-text role">Moderator</span></a>
        </div></li>
        <br>
        <li><a href="/conference/topics.jsp"><i class="material-icons">border_all</i>${varAllTopics}</a></li>
        <li>
        <div>
        	<ul class="collapsible" data-collapsible="accordion">
        	<li>
            <div class="collapsible-header"><i class="material-icons">mail_outline</i>${varNewTopics}</div>
            <div class="collapsible-body">
            <span>
                <div align="center">
                    <form name="editEventForm" method="POST" action="controller">
                    <input type="hidden" name="command" value="confspeakertopic"/>
                    <c:forEach items="${newspeakertopics}" var="nt" >
                    <p>
                      <input class="with-gap" name="idconftopic" type="radio" id="${nt.getId()}" value="${nt.getId()}" checked/>
                      <label for="${nt.getId()}"><c:out value="${nt.getTopic()} ${varSpeaker}: ${nt.getIdSpeaker()}" /></label>
                    </p>
                    </c:forEach>
                    <select name="topicstatus" required>
                        <option value="" disabled selected>${varConfirmOrCancel}</option>
                        <option value="confirm">${varConfirm}</option>
                        <option value="cancel">${varCancel}</option>
                    </select>
                    <div align="center">
                        <button class="btn waves-effect waves-light" type="submit" name="action">${varConfirmTopicBtn}
                        <i class="material-icons left">send</i>
                        </button>
                    </div>
                    </form>
                </div>
            </span>
            </div>
            </li>
        	<li>
        	<div class="collapsible-header"><i class="material-icons">subject</i>${varRegistrationStatistic}</div>
        	<div class="collapsible-body">
        	<span>
        	<div align="center">
        	    <h6>${varRegistrations}: <rs:RegistrationStatistic/> <i class="material-icons">person</i></h6>
        	    <h6>${varVisitors}: 10    <i class="material-icons">person</i></h6>
        	</div>
        	</span>
        	</div>
        	</li>
        	</ul>
        </div>
        </li>
        <li><div class="divider"></div></li>
        <li>
        <div>
            <ul class="collapsible" data-collapsible="accordion">
            <li>
            <div class="collapsible-header"><i class="material-icons">check_circle</i>${varConfirmedTopics}</div>
            <div class="collapsible-body">
            <span>
            <div align="center">
                <ul>
                    <c:forEach items="${conftopic}" var="ct" >
                        <li><c:out value="${ct.getTopic()}" /> - ${varSpeaker}: <c:out value="${ct.getIdSpeaker()}" /></li>
                    </c:forEach>
                </ul>
            </div>
            </span>
            </div>
            </li>
            </ul>
        </div>
        </li>
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
                                        </select><br>
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
                        <div class="collapsible-header"><i class="material-icons">edit_location</i>${varEditPlaceDate}</div>
                        <div class="collapsible-body">
                        <span>
                        <div>
                            <form name="loginForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="update"/>
                                <input type="hidden" name="id" value="${event.getId()}"/>
                                <input type="hidden" name="topic" value="${event.getTopic()}"/>
                                <div class="input-field">
                                    <label for="place">${varPlace}</label>
                                    <input id="place" type="text" class="validate" name="place" value="${event.getPlace()}" required>
                                </div>
                                <div class="input-field">
                                    <label for="date">${varDate}</label>
                                    <input id="date" type="text" class="datepicker" name="date" required>
                                </div>
                                <div class="input-field">
                                    <label for="time">${varTime}</label>
                                    <input id="time" type="text" class="timepicker" name="time" required>
                                </div>
                                <br>
                                <button class="btn waves-effect waves-light" type="submit" name="action">${varEditPlaceDateBtn}
                                    <i class="material-icons left">update</i>
                                </button>
                            </form>
                        </div>
                        </span>
                        </div>
                        </li>
                        <li>
                        <div class="collapsible-header"><i class="material-icons">edit</i>${varEditReport}</div>
                        <div class="collapsible-body">
                        <span>
                        <div>
                            <form name="loginForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="updatereport"/>
                                <input type="hidden" name="idevent" value="${event.getId()}"/>
                                <select name="idreport" required>
                                    <option value="" disabled selected>${varChooseReport}</option>
                                    <c:forEach items="${list}" var="rp" >
                                        <option value="${rp.getId()}"><c:out value="${rp.getTopic()}" /></option>
                                    </c:forEach>
                                </select>
                                <div class="input-field">
                                    <label for="topic">${varTopic}</label>
                                    <input id="topic" type="text" class="validate" name="topicreport" value="" required>
                                </div>
                                <div class="input-field">
                                    <label for="place">${varPlace}</label>
                                    <input id="place" type="text" class="validate" name="placereport" value="" required>
                                </div>
                                <div class="input-field">
                                    <label for="date">${varDate}</label>
                                    <input id="date" type="text" class="datepicker" name="datereport" required>
                                </div>
                                <div class="input-field">
                                    <label for="time">${varTime}</label>
                                    <input id="time" type="text" class="timepicker" name="timereport" required>
                                </div>
                                <select name="idspeaker" required>
                                    <option value="" disabled selected>${varChooseSpeaker}</option>
                                    <c:forEach items="${speakers}" var="sp" >
                                        <option value="${sp.getId()}"><c:out value="${sp.getLastName()}" /></option>
                                    </c:forEach>
                                </select>
                                <button class="btn waves-effect waves-light" type="submit" name="action">${varEditReportBtn}
                                    <i class="material-icons left">update</i>
                                </button>
                            </form>
                            <br>
                        </div>
                        </span>
                        </div>
                        </li>
                        <li>
                        <div class="collapsible-header"><i class="material-icons">message</i>${varProposeTopic}</div>
                        <div class="collapsible-body">
                        <span>
                            <div>
                                <form name="editEventForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value="proptopicforspeaker"/>
                                    <div class="input-field">
                                        <label for="topicfs">${varTopic}</label><br>
                                        <input id="topicfs" type="text" class="validate" name="topicforprop" required>
                                    </div>
                                    <select name="idspeaker" required>
                                        <option value="" disabled selected>${varChooseSpeaker}</option>
                                        <c:forEach items="${speakers}" var="s" >
                                            <option value="${s.getId()}"><c:out value="${s.getLastName()}" /></option>
                                        </c:forEach>
                                    </select>
                                    <br/>
                                    <button class="btn waves-effect waves-light" type="submit" name="action">${varProposeTopicBtn}
                                    <i class="material-icons right">send</i>
                                    </button>
                                </form>
                                <br>
                            </div>
                        </span>
                        </div>
                        </li>
                        <li>
                        <div class="collapsible-header"><i class="material-icons">email</i>${varSendEmails}</div>
                        <div class="collapsible-body">
                        <span>
                            <div>
                                <form name="editEventForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value="sendemail"/>
                                    <div class="input-field">
                                        <label for="topic">${varTopic}</label>
                                        <input type="text" name="emailtopic" class="validate" value="" required>
                                    </div>
                                    <div class="input-field">
                                        <label for="textarea1">${varText}</label>
                                        <textarea id="textarea1" class="materialize-textarea" name="emailtext"></textarea>
                                    </div>
                                    <br/>
                                    <br/>
                                    <button class="btn waves-effect waves-light" type="submit" name="action">${varSendEmailsBtn}
                                    <i class="material-icons right">send</i>
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
                    <form name="loginForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="changelang"/>
                        <input class="with-gap" name="lang" type="radio" id="1" value="EN" checked/>
                        <label for="1">English</label> |
                        <input class="with-gap" name="lang" type="radio" id="2" value="DE" checked/>
                        <label for="2">Deutsch</label> |
                        <input class="with-gap" name="lang" type="radio" id="3" value="RU" checked/>
                        <label for="3">Русский</label>
                        <br>
                        <br>
                        <br>
                        <button class="btn waves-effect waves-light" type="submit" name="action">${varChangeLangBtn}
                            <i class="material-icons left">autorenew</i>
                        </button>
                    </form>
                </div>
                <br>
                <div align="center">
                    <a href="#" data-activates="slide-out" class="button-collapse"><i class="medium material-icons">playlist_add_check</i></a>
                </div>
            </div>
            <div class="col s4 offset-s1">
                <div>
                    <h5>${varAllReports}</h5>
                    <ul class="collection">
                        <c:forEach items="${list}" var="i" >
                            <li class="collection-item avatar">
                                <i class="material-icons circle">event_available</i>
                                <span class="title"><p class="flow-text"><c:out value="${i.getTopic()}" /></p></span>
                                <p>${varPlace}: <c:out value="${i.getPlace()}" /><br>
                                    <fmt:formatDate value="${i.getCalendar().getTime()}" type="both" timeStyle = "short"/>
                                </p>
                                <p>${varSpeaker}: ${i.getSpeakerLastName()}</p>
                                <a class="secondary-content"><i class="material-icons">grade</i></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <br>
                <div>
                    <h5><c:out value="${msg}" /></h5>
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
            $(".button-collapse").sideNav();
       </script>
    </body>
</html>
