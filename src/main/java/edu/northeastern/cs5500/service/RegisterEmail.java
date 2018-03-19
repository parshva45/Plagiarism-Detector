package edu.northeastern.cs5500.service;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Praveen Singh
 */
@Component
public class RegisterEmail {
    private static final Logger LOGGER = Logger.getLogger(RegisterEmail.class.getName());

    public void sendEmail(String name, String email) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("msdteam207@gmail.com",
                                "Spring2018Team207");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("msdteam207@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,

                    InternetAddress.parse(email));
            message.setSubject("PlagiarismDetector");
            message.setText("Dear " + name +
                    "\n\n This is PlagDetector! Thanks for Registering! :)" +
                    "\n\n - Team 207");

            Transport.send(message);

        } catch (MessagingException e) {
            LOGGER.log(Level.ALL, "Exception sending email  : {0}", e.getMessage());
        }
    }
}
