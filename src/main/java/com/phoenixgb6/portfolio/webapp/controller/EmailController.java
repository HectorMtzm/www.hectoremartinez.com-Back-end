package com.phoenixgb6.portfolio.webapp.controller;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping
public class EmailController {

    private final JavaMailSender emailSender;

    public EmailController(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @PostMapping("/contact/submit")
    public RedirectView sendEmail(@RequestParam String name, @RequestParam String email, @RequestParam String message, RedirectAttributes attributes) {

        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo("hector.mtzm6@gmail.com");
            mail.setSubject("CONTACT ME SOLICITATION");
            mail.setText("EMAIL: " + email + "\nNAME: " + name + "\n\n" + message);
            emailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        attributes.addFlashAttribute("sent", true);

        return new RedirectView("/contact", true);
    }
}
