package com.conference.service;

import com.conference.persistence.dao.MySqlDaoFactory;
import com.conference.persistence.entity.User;
import com.conference.persistence.idao.IFactory;
import com.conference.persistence.idao.IGeneric;
import com.conference.web.properties.EmailProperties;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by gleb on 11.01.18.
 */
public class EmailService {
    private static final Logger LOG = Logger.getLogger(EmailService.class.getName());
    private static IFactory factory = new MySqlDaoFactory();
    private static String recipient = "ggleb203@gmail.com ,qlp@ukr.net ";

    public static void sendEmailToAllUsers(String subject, String text) throws AddressException {
        List<String> emailList = new ArrayList<>();
        List<User> users;
        try (Connection connection = (Connection) factory.getContext()) {
            IGeneric userDao = factory.getDao(connection, User.class);
            users = userDao.getAll();
            for (User u : users) {
                recipient += ", " + u.getEmail();
            }
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }

        String[] recipientList = recipient.split(",");
        InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
        int counter = 0;
        for (String recipient : recipientList) {
            recipientAddress[counter] = new InternetAddress(recipient.trim());
            counter++;
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EmailProperties.getInstance().getProperty(EmailProperties.USER_NAME),
                                EmailProperties.getInstance().getProperty(EmailProperties.PASSWORD));
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("systemconferencemanagement@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, recipientAddress);
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            //System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
