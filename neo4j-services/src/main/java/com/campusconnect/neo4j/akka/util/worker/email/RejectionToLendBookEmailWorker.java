package com.campusconnect.neo4j.akka.util.worker.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import akka.actor.UntypedActor;

import com.campusconnect.neo4j.akka.util.task.AcceptionToLendBookTask;
import com.campusconnect.neo4j.akka.util.task.RejectionToLendBookEmailTask;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.User;

public class RejectionToLendBookEmailWorker extends UntypedActor{
	
	
	 public final static String SUBJECT_TITLE = "%1$s, Refused to lend the book %2$s to you";

	    @Override
	    public void onReceive(Object message) throws Exception {
	        if (message instanceof RejectionToLendBookEmailTask) {
	            Properties mailServerProperties;
	            Session getMailSession;
	            MimeMessage generateMailMessage;

	            RejectionToLendBookEmailTask emailTask = (RejectionToLendBookEmailTask) message;

	            mailServerProperties = System.getProperties();
	            mailServerProperties.put("mail.smtp.port", "587");
	            mailServerProperties.put("mail.smtp.auth", "true");
	            mailServerProperties.put("mail.smtp.starttls.enable", "true");

	            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
	            generateMailMessage = new MimeMessage(getMailSession);
	            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTask.getBorrower().getEmail()));
	            String subjectLine = getSubjectLine(emailTask.getOwner(), emailTask.getBook());
	            generateMailMessage.setSubject(subjectLine);
	            String emailBody = "<h3>" + emailTask.getOwner().getName() + "refused to lend the book to you" + " </h3>";
	            String messageByOwner = emailTask.getRejectionMessage();
	            emailBody += "<br/><p>" + messageByOwner + "</p>";
	            generateMailMessage.setContent(emailBody, "text/html");

	            Transport transport = getMailSession.getTransport("smtp");

	            transport.connect("smtp.gmail.com", "admin@bookforborrow.com", "password123_1");
	            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
	            transport.close();
	        }

//		    public static void main(String[] args) throws Exception {
//		        BorrowInitEmailWorker borrowInitEmailWorker = new BorrowInitEmailWorker();
//		        borrowInitEmailWorker.onReceive(new Object());
//		    }

	    }

	    private String getSubjectLine(User fromUser, Book book) {
	        return String.format(SUBJECT_TITLE, fromUser.getName(), book.getName());
	    }
	}


