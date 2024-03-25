package ru.trofimov.todomanager.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import ru.trofimov.todomanager.service.impl.EmailServiceImpl;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {

    @Mock
    private JavaMailSender mailSender;

    private EmailService emailService;

    @Before
    public void setup() {
        emailService = new EmailServiceImpl(mailSender);
    }

    @Test
    public void testSendEmailToUser() {
        String userEmail = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Email Body";

        emailService.sendEmailToUser(userEmail, subject, body);

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo(userEmail);
        expectedMessage.setSubject(subject);
        expectedMessage.setText(body);

        verify(mailSender).send(expectedMessage);
    }
}
