package com.springboot.mailsender.mailsenderapp.Services;

import java.io.File;
import java.io.InputStream;

public interface EmailService {
    // send email to single person
    void sendEmail(String recipient, String subject, String body);
    // send email to multiple people
    void sendEmail(String[] recipients, String subject, String body);
    // send email with attachment
    void sendEmailWithHtml(String recipient, String subject, String htmlContent);
   // void send email with attachments
    void sendEmailWithAttachments(String recipient, String subject, String message, File file);
    //void send email with attachment using input stream
    void sendEmailWithAttachments(String recipient, String subject, String message, InputStream is);
}
