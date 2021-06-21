package my.project.QPortal.service.impl;

import my.project.QPortal.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService
{
    private final JavaMailSender emailSender;

    @Override
    public void send(String to, String subject, String text)
    {
        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try
        {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            this.emailSender.send(message);
        }
        catch (MessagingException messageException)
        {
            throw new RuntimeException(messageException);
        }
    }

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender)
    {
        this.emailSender = emailSender;
    }
}
