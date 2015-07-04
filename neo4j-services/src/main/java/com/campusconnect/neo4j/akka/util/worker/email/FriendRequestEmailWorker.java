package com.campusconnect.neo4j.akka.util.worker.email;

import akka.actor.UntypedActor;
import com.campusconnect.neo4j.akka.util.task.FriendRequestEmailTask;
import com.campusconnect.neo4j.types.neo4j.User;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class FriendRequestEmailWorker extends UntypedActor {

    public final static String SUBJECT_TITLE = "%1$s, sent friend request to you";
    public final static String URL = "http://localhost:3000/";

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof FriendRequestEmailTask) {
            Properties mailServerProperties;
            Session getMailSession;
            MimeMessage generateMailMessage;

            FriendRequestEmailTask emailTask = (FriendRequestEmailTask) message;

            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");

            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTask.getFriend().getEmail()));
            String subjectLine = getSubjectLine(emailTask.getUser());
            generateMailMessage.setSubject(subjectLine);
            String emailBody = "<h3>Click <a href=\"" + URL + "\">here</a> to accept</h3><br />";
            generateMailMessage.setContent(emailBody, "text/html");

            Transport transport = getMailSession.getTransport("smtp");

            transport.connect("smtp.gmail.com", "admin@bookforborrow.com", "password123_1");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
        }
//	    public static void main(String[] args) throws Exception {
//	        BorrowInitEmailWorker borrowInitEmailWorker = new BorrowInitEmailWorker();
//	        borrowInitEmailWorker.onReceive(new Object());
//	    }
    }

    private String getSubjectLine(User fromUser) {
        return String.format(SUBJECT_TITLE, fromUser.getName());
    }
//	    private String getUrlForRedirection(User fromUser, Book book, User toUser) {
//	        return String.format(URL, book.getId(), toUser.getId(), fromUser.getId());
//	    }


}
