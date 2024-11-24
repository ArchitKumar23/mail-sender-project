package com.springboot.mailsender.mailsenderapp.helper;


import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class EmailRequest {
    private String to;
    private String subject;
    private String message;
}
