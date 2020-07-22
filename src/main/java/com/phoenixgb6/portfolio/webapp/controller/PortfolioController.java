package com.phoenixgb6.portfolio.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping
public class PortfolioController {

    @Autowired
    private JavaMailSender emailSender;

    @GetMapping("/resume")
    public ResponseEntity<Resource> downloadResume() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resume.pdf");

        File file = ResourceUtils.getFile("classpath:static/portfolio/resume.pdf");
        Path path = Paths.get(file.getAbsolutePath());

        ByteArrayResource resume = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resume);
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

//        return "redirect:/portfolio/contact";
        return new RedirectView("/contact", true);
    }
}
