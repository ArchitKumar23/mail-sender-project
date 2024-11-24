package com.springboot.mailsender.mailsenderapp.impl;

import com.springboot.mailsender.mailsenderapp.Services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;

    private Logger logger= LoggerFactory.getLogger(EmailServiceImpl.class);

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String recipient, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom("gigda477@gmail.com");
     mailSender.send(simpleMailMessage);
     logger.info("Email sent successfully ..");
    }

    @Override
    public void sendEmail(String[] recipients, String subject, String body) {
        SimpleMailMessage  simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipients);
            simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom("gigda477@gmail.com");
        mailSender.send(simpleMailMessage);

    }

    @Override
    public void sendEmailWithHtml(String recipient, String subject, String htmlContent) {

        MimeMessage simpleMailMessage = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage,true,"UTF-8");
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setFrom("gigda477@gmail.com");
            helper.setText(htmlContent, true);
            mailSender.send(simpleMailMessage);
            logger.info("Email has been sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailWithAttachments(String recipient, String subject, String message, File file) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setFrom("gigda477@gmail.com");
            helper.setText(message);
            FileSystemResource fileSystemResource=new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(), file);
            mailSender.send(mimeMessage);
            logger.info("Email with attachment has been sent");
        }catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailWithAttachments(String recipient, String subject, String message, InputStream is) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setFrom("gigda477@gmail.com");
            helper.setText(message);
            File file = new File("src/main/resources/email/test.png");
            Files.copy(is,file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource=new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(), file);
            mailSender.send(mimeMessage);
            logger.info("Email with attachment has been sent");
        }catch (MessagingException e) {
            throw new RuntimeException(e);
        }catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
