package edu.northeastern.cs5500.service;

import org.junit.Before;
import org.junit.Test;

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

}