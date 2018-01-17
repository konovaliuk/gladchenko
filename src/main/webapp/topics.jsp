<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.conference.service.TopicService"
    import="com.conference.persistence.entity.Topic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
<%
    List<Topic> topics = new TopicService().getAllTopics();
%>
<html>
    <head>
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <link type="text/css" rel="stylesheet" href="css/main.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Conference</title>
    </head>
    <body>
        <c:set var="topics" scope="session" value="<%=topics%>"/>
        <c:set var="totalCount" scope="session" value="<%=topics.size()%>"/>
        <c:set var="perPage" scope="session"  value="5"/>
        <c:set var="pageStart" value="${param.start}"/>
        <nav class="cyan darken-4">
            <div class="nav-wrapper">
                <a href="/conference" class="brand-logo center">Conference<i class="material-icons">event</i></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="#">${varAllTopics}</a></li>
                </ul>
            </div>
        </nav>
        <br>
        <div class="row">
            <div class="col s5 offset-s1">
                <h5>${varAllTopics}:</h5>
                <c:if test="${empty pageStart or pageStart < 0}">
                       <c:set var="pageStart" value="0"/>
                </c:if>
                <c:if test="${totalCount < pageStart}">
                       <c:set var="pageStart" value="${pageStart - 5}"/>
                </c:if>
                    <a href="?start=${pageStart - 5}"><<</a>${pageStart + 1} - ${pageStart + 5}
                    <a href="?start=${pageStart + 5}">>></a>

                <ul class="collection">
                   <c:forEach var="topic" items="${topics}" varStatus="letterCounter"
                                        begin="${pageStart}" end="${pageStart + perPage - 1}">
                        <li class="collection-item">
                            Topic: <c:out value="${topic.getTopic()}" /><br>
                            ID Speaker:<c:out value="${topic.getIdSpeaker()}" /><br>
                            Status: <c:out value="${topic.getStatus()}" /><br>
                        </li>
                   </c:forEach>
                </ul>
                <br>
                <div>
                    <form name="editEventForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="backtomoder"/>
                        <button class="btn waves-effect waves-light btn-large" type="submit" name="action">Back
                        <i class="material-icons left">arrow_back</i>
                        </button>
                    </form>
                    <br>
                </div>
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
    </body>
</html>