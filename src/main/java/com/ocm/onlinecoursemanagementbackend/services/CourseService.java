package com.ocm.onlinecoursemanagementbackend.services;

import com.ocm.onlinecoursemanagementbackend.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
}
