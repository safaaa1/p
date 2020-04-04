/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author safa
 */
public class Mail {
     public static void sendMail(String recepient) throws MessagingException {
        System.out.println("begin mail send");

        Properties properties = new Properties();

        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String monEmail = "safa.doghmani@esprit.tn";
        String mdp = "";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(monEmail, mdp);
            }
            
        });
        
        Message message = prepareMessage(session, monEmail);
        Transport.send(message);
        System.out.println("message has been sent");
    }
    
    private static Message prepareMessage(Session session, String monMail) throws AddressException, MessagingException{
        
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(monMail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(monMail));
        message.setSubject("");
        message.setText("!");
        return message;
    }
}
