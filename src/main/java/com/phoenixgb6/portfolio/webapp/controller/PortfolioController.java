package com.phoenixgb6.portfolio.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.io.InputStream;


@Controller
@RequestMapping
public class PortfolioController {

    @Autowired
    private JavaMailSender emailSender;


    @GetMapping("/portfolio")
    public String portfolio(){
        return "portfolio/portfolio";
    }

    @GetMapping("/portfolio/universitycrm")
    public String universitycrm(){
        return "redirect:/portfolio/universitycrm/students";
    }

    @GetMapping("/portfolio/universitycrm/api")
    public String universitycrmrest(){
        return "portfolio/universitycrmrest";
    }

    @GetMapping("/contact")
    public String contact(){
         return "portfolio/contact";
    }

    @PostMapping("/contact/submit")
    public RedirectView sendEmail(@RequestParam String name, @RequestParam String email, @RequestParam String message, RedirectAttributes attributes) {

        try{
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

    @GetMapping("/resume")
    public ResponseEntity<Resource> downloadResume() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        ByteArrayResource resume = null;

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Full-stack-Java-developer__Hector-Martinez__Resume.pdf");

        try{
            InputStream is = new ClassPathResource("static/portfolio/resume.pdf").getInputStream();
            resume = new ByteArrayResource(is.readAllBytes());
        } catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resume);
    }
}
