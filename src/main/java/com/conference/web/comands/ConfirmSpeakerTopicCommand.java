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
 * Created by gleb on 12.01.18.
 */
public class ConfirmSpeakerTopicCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        Topic topic = TopicService.getTopicById(Long.parseLong(request.getParameter("idconftopic")));
        String status = request.getParameter("topicstatus");
        if (status.equalsIgnoreCase("confirm")) {
            topic.setStatus("confmod");
        } else {
            topic.setStatus("cancel");
        }
        TopicService.updateTopic(topic);
        HttpSession session = request.getSession(true);
        session.setAttribute("newspeakertopics", TopicService.getTopicsByParam("status", "newsp"));
        return ConfigProperties.getInstance().getProperty(ConfigProperties.MODER_PAGE_PATH);
    }
}
