package com.springboot.mailsender.mailsenderapp;

import com.springboot.mailsender.mailsenderapp.Services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
public class EmailSenderTest {

    @Autowired
    private EmailService emailService;

    @Test
    void emailSendTest() {
        System.out.println("sending email...");
        emailService.sendEmail("kumararchit58@gmail.com", "Email from Email Sender App", "This is a Spring Boot App");
    }

    @Test
    void sendHtmlInEmail() {
        String html = "" + "<h1 style='color:blue;border:1px solid red ;'>Welcome to the html code</h1>" + "";
        emailService.sendEmailWithHtml("kumararchit58@gmail.com", "Hello Everyone from archit", html);
    }

    @Test
    void sendEmailWithFile() {
        emailService.sendEmailWithAttachments("kumararchit58@gmail.com", "Email with file", "This email contain file", new File("D:/Full Stack Project/Mail Sender App/mailsenderapp/src/main/resources/static/top-view-black-frame-with-plant.png"));
    }
    @Test
    void sendEmailWithStream() {
        File file = new File("D:/Full Stack Project/Mail Sender App/mailsenderapp/src/main/resources/static/top-view-black-frame-with-plant.png");
        try {
            InputStream is = new FileInputStream(file);
            emailService.sendEmailWithAttachments("kumararchit58@gmail.com", "Email with file", "This email contain file", is);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
