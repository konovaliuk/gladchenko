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
 * Created by gleb on 02.01.18.
 */
public class ConfirmTopicCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistException {
        Topic topic = TopicService.getTopicById(Long.parseLong(request.getParameter("idconftopic")));
        String status = request.getParameter("topicstatus");
        if (status.equalsIgnoreCase("confirm")) {
            topic.setStatus("confirmed");
        } else {
            topic.setStatus("cancel");
        }
        TopicService.updateTopic(topic);
        HttpSession session = request.getSession(true);
        session.setAttribute("newtopic", TopicService.getNewTopicsByParam("id_speaker",
                                                                String.valueOf(topic.getIdSpeaker())));
        return ConfigProperties.getInstance().getProperty(ConfigProperties.SPEAKER_PAGE_PATH);
    }
}
