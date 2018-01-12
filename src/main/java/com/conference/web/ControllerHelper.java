package com.conference.web;

import com.conference.web.comands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by gleb on 19.12.17.
 */
public class ControllerHelper {
    private static ControllerHelper instance = null;
    private HashMap<String, ICommand> commands = new HashMap<>();

    public ControllerHelper() {
        commands.put("login", new LoginCommand());
        commands.put("update", new UpdateEventCommand());
        commands.put("updatereport", new UpdateReportCommand());
        commands.put("reportregistr", new RegistrationCommand());
        commands.put("proptopicforspeaker", new ProposeTopicForSpeakerCommand());
        commands.put("conftopic", new ConfirmTopicCommand());
        commands.put("proptopicformoder", new ProposeTopicForModerCommand());
        commands.put("backtomoder", new BackToMainPageCommand());
        commands.put("eventchange", new ChangeEventCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("changelang", new ChangeLanguageCommand());
        commands.put("sendemail", new SendEmailCommand());
        commands.put("confspeakertopic", new ConfirmSpeakerTopicCommand());
    }

    /**
     * Создание единственного объекта по шаблону Singleton
     * @return
     */
    public static ControllerHelper getInstance(){
        if (instance == null){
            instance = new ControllerHelper();
        }
        return instance;
    }

    /**
     * Создание объекта, соответствующего команде
     * @param request
     * @return
     */
    public ICommand getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        ICommand command = commands.get(action);
        if (command == null){
            command = new NoCommand();
        }
        return command;
    }
}
