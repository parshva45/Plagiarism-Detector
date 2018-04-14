package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.response.PlagiarismReportJSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Praveen Singh
 */
@Component
public class EmailService {
    private static final Logger LOGGER = LogManager.getLogger(EmailService.class);

    /**
     * Method to send email to user for registration.
     * @param name String name
     * @param email String email id
     */
    public void sendEmail(String name, String email, List<PlagiarismReportJSON> report) {
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
            if(report == null) {
                message.setText("Dear " + name +
                        "\n\n This is PlagDetector! Thanks for Registering! :)" +
                        "\n\n - Team 207");
                LOGGER.info("sending register confirm email to user {} at email id {}",
                        name, email);
            }else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Dear ").append(name).append("\t Plagiarism found for following users: \n \n");
                for (PlagiarismReportJSON plagiarismReport:report) {
                    stringBuilder.append(plagiarismReport.toString()).append("\n \n");
                }
                message.setText(stringBuilder.toString());
                LOGGER.info("sending plagiarism report to user {} at email id {}",
                        name, email);
            }
            Transport.send(message);
            LOGGER.info("successfully sent email");

        } catch (MessagingException e) {
            LOGGER.error("Exception sending email  : {}", e.getMessage());
        }
    }
}
