package com.springboot.mailsender.mailsenderapp.Controller;

import com.springboot.mailsender.mailsenderapp.Services.EmailService;
import com.springboot.mailsender.mailsenderapp.helper.CustomResponse;
import com.springboot.mailsender.mailsenderapp.helper.EmailRequest;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/mail")
public class EmailController {
    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    // handle incoming email requests

    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
        emailService.sendEmailWithHtml(request.getTo(), request.getSubject(), request.getMessage());
        return ResponseEntity.ok(CustomResponse.builder().message("Email Send SuccessFully..!!").httpStatus(HttpStatus.OK).success(true).build());
    }
    @PostMapping("/send-with-file")
    public ResponseEntity<CustomResponse> sendWithFile(@RequestBody EmailRequest request, @RequestParam MultipartFile file) throws IOException {
        emailService.sendEmailWithAttachments(request.getTo(),request.getSubject(),request.getMessage(),file.getInputStream());
        return ResponseEntity.ok(CustomResponse.builder().message("Email Send Sucessfully..!!").httpStatus(HttpStatus.OK).success(true).build());

    }
}
