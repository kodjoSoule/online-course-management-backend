package com.ocm.onlinecoursemanagementbackend.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    //test message
    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

}
