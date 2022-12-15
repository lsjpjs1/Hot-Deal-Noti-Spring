package com.example.hotdealnoti.common.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component
@RequiredArgsConstructor
@Slf4j
public class MailUtil {

    private final JavaMailSender sender;

    public void sendMail(MailDTO.MailRequest mailRequest) throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setTo(mailRequest.getReceiverEmail());
        mimeMessageHelper.setSubject(mailRequest.getSubject());
        mimeMessageHelper.setText(mailRequest.getBody());
        sender.send(mimeMessage);


    }
}
