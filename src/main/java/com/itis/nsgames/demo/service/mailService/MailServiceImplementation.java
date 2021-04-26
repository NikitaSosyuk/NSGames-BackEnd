package com.itis.nsgames.demo.service.mailService;

import com.itis.nsgames.demo.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailServiceImplementation implements MailService {
    private final JavaMailSender mailSender;
    private final UserService userService;

    @Value("${spring.mail.username}")
    private String from;

    public MailServiceImplementation(JavaMailSender mailSender, UserService userService) {
        this.mailSender = mailSender;
        this.userService = userService;
    }

    public boolean sendCodeToTheMail(String email) {
        if (userService.userIsExist(email)) {
            String uuid = UUID.randomUUID().toString().substring(0, 10);
            userService.setCode(email, uuid);
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setTo(email);
            mailMessage.setFrom("NSGames@noreply.com");
            mailMessage.setSubject("Код для воссатновления  NSGames");
            mailMessage.setText("Код для восстановления пароля - \n \n" + uuid + "\n \nВведите его в своем приложении");

            mailSender.send(mailMessage);
            return true;
        }
        return false;
    }
}
