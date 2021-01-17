package com.phoenixgb6.portfolio.webapp.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;


@Controller
@RequestMapping
public class RobotsController {

    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> robots() throws IOException {
        InputStream is = new ClassPathResource("static/portfolio/robots.txt").getInputStream();
        return ResponseEntity
                .ok()
                .body(new ByteArrayResource(is.readAllBytes()));
    }
}
