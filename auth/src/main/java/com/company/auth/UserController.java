package com.company.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth/v1")
public class UserController {
    @Value("${spring.mail.username}")
    private String from;


    @Autowired
    private EmailVerificationService emailService;

    private String savedCode = "";

    @PostMapping("/register")
    public String sendVerificationCode(@RequestParam String email) {
        emailService.sendVerificationEmail(email);
        return "Verification code sent to " + email;
    }

    @PostMapping("/check-code")
    public String checkVerificationCode(@RequestParam String code) {
        if (savedCode.equals(code)) {
            return "Email verified";
        }
        return "Invalid verification code!" +
                "Please try again later.";
    }





}
