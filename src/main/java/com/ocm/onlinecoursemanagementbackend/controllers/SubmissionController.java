package com.ocm.onlinecoursemanagementbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
    //test message
    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

}