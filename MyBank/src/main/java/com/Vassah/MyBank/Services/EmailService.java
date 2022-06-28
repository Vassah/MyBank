package com.Vassah.MyBank.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Vassah.MyBank.model.User;

import lombok.AllArgsConstructor;

@Service
@Component
@AllArgsConstructor
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "mybankapplicationjava@mail.ru";
        String senderName = "My Bank";
        String subject = "Подтверждение регистрации";
        String content = "Dear [[name]],<br>"
                + "Пожалуйста, перейдите по ссылке ниже чтобы подтвердить регистрацию:<br>"
                + "<h3><a href=\"localhost:8080[[URL]]\" target=\"_self\">Подтвердить</a></h3>"
                + "<a href=\"localhost:8080[[URL]]\">localhost:8080[[URL]] </a><br>"
                + "Благодарим вас,<br>"
                + "команда My Bank";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.fullName());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }
    
}
