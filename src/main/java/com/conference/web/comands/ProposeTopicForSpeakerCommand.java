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
import java.util.Locale;

/**
 * Created by gleb on 01.01.18.
 */
public class ProposeTopicForSpeakerCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        Topic topic = new Topic();
        topic.setTopic(request.getParameter("topicforprop"));
        topic.setIdSpeaker(Long.parseLong(request.getParameter("idspeaker")));
        topic.setStatus("new");
        topic = TopicService.createTopic(topic);
        String message = "Propose topic " + topic.getTopic() + " successful!";
        HttpSession session = request.getSession(true);
        session.setAttribute("msg", message);
        return ConfigProperties.getInstance().getProperty(ConfigProperties.MODER_PAGE_PATH);
    }
}
