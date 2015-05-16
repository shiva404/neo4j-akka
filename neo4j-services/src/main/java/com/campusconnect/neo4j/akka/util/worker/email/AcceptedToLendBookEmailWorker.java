package com.campusconnect.neo4j.akka.util.worker.email;

import akka.actor.UntypedActor;
import com.campusconnect.neo4j.akka.util.task.AcceptionToLendBookTask;
import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.User;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class AcceptedToLendBookEmailWorker extends UntypedActor {

    public final static String SUBJECT_TITLE = "%1$s, accepted to lend the book %2$s to you";

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof AcceptionToLendBookTask) {
            Properties mailServerProperties;
            Session getMailSession;
            MimeMessage generateMailMessage;

            AcceptionToLendBookTask emailTask = (AcceptionToLendBookTask) message;

            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");

            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTask.getBorrower().getEmail()));
            String subjectLine = getSubjectLine(emailTask.getOwner(), emailTask.getBook());
            generateMailMessage.setSubject(subjectLine);
            String emailBody = "<h3>Find the email details of the book owner " + emailTask.getOwner().getEmail() + " </h3>";
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

    private String getSubjectLine(User fromUser, Book book) {
        return String.format(SUBJECT_TITLE, fromUser.getName(), book.getName());
    }
}
