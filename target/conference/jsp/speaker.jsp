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
        <br>
        <div class="row">
            <div class="col s4 offset-s1">
                <div class="card">
                    <div class="card-content">
                        <h4><c:out value="${event.getTopic()}" /></h4>
                        <h5><i class="material-icons">place</i> - <c:out value="${event.getPlace()}" /></h5>
                        <h5><i class="material-icons">date_range</i> - <fmt:formatDate value="${event.getCalendar().getTime()}" type="date"/></h5>
                        <h5><i class="material-icons">timer</i> - <fmt:formatDate value="${event.getCalendar().getTime()}" type="time" timeStyle = "short"/></h5>
                    </div>
                </div>
            </div>
            <div class="col s3 offset-s2">
                <div class="card">
                    <div class="card-content" align="center">
                        <h5>Rating: <c:out value="${salary.getRating()}" /> - <i class="material-icons">star_border</i></h5>
                        <br>
                        <h5>Bonus: <c:out value="${salary.getBonus()}" /> - <i class="material-icons">attach_money</i></h5>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="divider"></div>
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
            <div class="col s3 offset-s1">
                <h5>Propose topic:</h5>
                <form name="editEventForm" method="POST" action="controller">
                    <input type="hidden" name="command" value="proptopictomoder"/>
                    <i class="material-icons prefix">mode_edit</i>
                    <label for="icon_prefix2">Topic:</label><br>
                    <textarea id="icon_prefix2" class="materialize-textarea" required></textarea>
                    <br/>
                    <br/>
                    <button class="btn waves-effect waves-light" type="submit" name="action">Send topic
                    <i class="material-icons right">send</i>
                    </button>
                </form>
                <br>
                <br>
                <h5>You have new topics:</h5>
                <br>
                <form name="editEventForm" method="POST" action="controller">
                    <input type="hidden" name="command" value="conftopic"/>
                    <c:forEach items="${newtopic}" var="nt" >
                        <p>
                          <input class="with-gap" name="idconftopic" type="radio" id="${nt.getId()}" value="${nt.getId()}" checked/>
                          <label for="${nt.getId()}"><c:out value="${nt.getTopic()}" /></label>
                        </p>
                    </c:forEach>
                    <br>
                    <button class="btn waves-effect waves-light" type="submit" name="action">Confirm
                        <i class="material-icons left">send</i>
                    </button>
                </form>
            </div>
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
    </body>
</html>