package com.company.auth;

import com.company.state.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service

public class EmailVerificationService {


    JavaMailSender javaMailSender;



    private Status status;
    private List<User> users;
    private User user;
    private final Random random = new Random();
    public String sendVerificationEmail(String toEmail) {
        String verificationCode = generateVerificationCode();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Email Verification Code");
        message.setText("Your verification code is: " + verificationCode);

        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verificationCode;
    }

    private String generateVerificationCode() {
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    private Map<String, String> verificationCodes = new HashMap<>();

    public String register(User user, Status status) {
        if (status.equals(Status.REGISTER)) {
            user.setFull_name(user.getFull_name());
            user.setEmail(user.getEmail());

            if (verificationCodes.containsKey(user.getEmail())) {
                verificationCodes.put(user.getEmail(), generateVerificationCode());
                verificationCodes.equals(verificationCodes);
            }

            return "Success";
        }
        return "Error";
    }


}
