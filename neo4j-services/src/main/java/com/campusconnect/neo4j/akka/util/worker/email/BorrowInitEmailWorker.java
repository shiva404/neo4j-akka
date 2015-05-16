package com.campusconnect.neo4j.akka.util.worker.email;

import akka.actor.UntypedActor;
import com.campusconnect.neo4j.akka.util.task.BorrowBookInitEmailTask;
import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.User;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by sn1 on 5/1/15.
 */
public class BorrowInitEmailWorker extends UntypedActor {

    public final static String SUBJECT_TITLE = "%1$s, wants to borrow %2$s from you";
    public final static String URL = "http://localhost:3000/process/books/%1$s/owner/%2$s/borrower/%3$s/borrowBookInit";

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof BorrowBookInitEmailTask) {
            Properties mailServerProperties;
            Session getMailSession;
            MimeMessage generateMailMessage;

            BorrowBookInitEmailTask emailTask = (BorrowBookInitEmailTask) message;

            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");

            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTask.getOwner().getEmail()));
            String subjectLine = getSubjectLine(emailTask.getBorrower(), emailTask.getBook());
            generateMailMessage.setSubject(subjectLine);
            String emailBody = "<h3>Click below to update data</h3><br /><a href=\"" + getUrlForRedirection(emailTask.getBorrower(), emailTask.getBook(), emailTask.getOwner()) + "\"/>";
            generateMailMessage.setContent(emailBody, "text/html");

            Transport transport = getMailSession.getTransport("smtp");

            transport.connect("smtp.gmail.com", "admin@bookforborrow.com", "password123_1");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
        }

//    public static void main(String[] args) throws Exception {
//        BorrowInitEmailWorker borrowInitEmailWorker = new BorrowInitEmailWorker();
//        borrowInitEmailWorker.onReceive(new Object());
//    }

    }

    private String getSubjectLine(User fromUser, Book book) {
        return String.format(SUBJECT_TITLE, fromUser.getName(), book.getName());
    }

    private String getUrlForRedirection(User fromUser, Book book, User toUser) {
        return String.format(URL, book.getId(), toUser.getId(), fromUser.getId());
    }

}
