package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.response.PlagiarismReportJSON;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Praveen Singh
 */
public class EmailServiceTest {

    private EmailService emailService;

    @Before
    public void setUp(){
        emailService = new EmailService();
    }

    @Test(expected = RuntimeException.class)
    public void sendEmailShouldThrowException(){
        emailService.sendEmail("praveen", null, null);
    }

    @Test
    public void sendEmailShouldActuallySendEmail(){
        emailService.sendEmail("praveen", "msdteam207@gmail.com", null);
    }

    @Test
    public void testSendEmailReport(){
        List<PlagiarismReportJSON> list = new ArrayList<>();
        list.add(new PlagiarismReportJSON().withFirstUser("praven")
                .withSecondUser("singh").withScore(40.0));
        emailService.sendEmail("praveen", "msdteam207@gmail.com", list);

    }

}