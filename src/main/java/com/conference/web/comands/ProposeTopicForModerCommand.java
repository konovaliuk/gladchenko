package com.conference.web.comands;

import com.conference.persistence.dao.PersistException;
import com.conference.persistence.entity.Topic;
import com.conference.service.TopicService;
import com.conference.web.properties.ConfigProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by gleb on 05.01.18.
 */
public class ProposeTopicForModerCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        Topic topic = new Topic();
        topic.setTopic(request.getParameter("topicformoder"));
        topic.setIdSpeaker(Long.parseLong(request.getParameter("idspeaker")));
        topic.setStatus("newsp");
        topic = TopicService.createTopic(topic);
        String message = "Propose topic " + topic.getTopic() + " successful!" ;
        HttpSession session = request.getSession(true);
        session.setAttribute("msg", message);
        return ConfigProperties.getInstance().SPEAKER_PAGE_PATH;
    }
}
