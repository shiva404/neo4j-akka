package com.campusconnect.neo4j.akka.util.worker.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import akka.actor.UntypedActor;

import com.campusconnect.neo4j.akka.util.task.AcceptionToLendBookTask;
import com.campusconnect.neo4j.akka.util.task.SuccessfulTransactionEmailTask;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.User;

public class SucessTransactionEmailWorker  extends UntypedActor {

	//TODO : Add proper text every where 
    public final static String SUBJECT_TITLE = "Sucessful transaction of book";

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof SucessTransactionEmailWorker) {
            Properties mailServerProperties;
            Session getMailSession;
            MimeMessage generateMailMessage;

            SuccessfulTransactionEmailTask emailTask = (SuccessfulTransactionEmailTask) message;

            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");

            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
    
            InternetAddress[] internetAddresses = {new InternetAddress(emailTask.getBorrower().getEmail()),new InternetAddress(emailTask.getOwner().getEmail())};
            
            generateMailMessage.setRecipients(Message.RecipientType.TO, internetAddresses);
        
    
            String emailBody = "The Book" + emailTask.getBook().getName() + "was successfully exchanged"+ "<br/> Thank you for using Book$Borrow"+" </h3>";
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
