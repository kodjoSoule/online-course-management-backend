package com.ocm.onlinecoursemanagementbackend.service;

import com.ocm.onlinecoursemanagementbackend.exception.ResourceNotFoundException;
import com.ocm.onlinecoursemanagementbackend.model.Assignment;
import com.ocm.onlinecoursemanagementbackend.model.Course;
import com.ocm.onlinecoursemanagementbackend.repository.AssignmentRepository;
import com.ocm.onlinecoursemanagementbackend.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, CourseRepository courseRepository) {
        this.assignmentRepository = assignmentRepository;
        this.courseRepository = courseRepository;
    }

    // Récupérer tous les devoirs d'un cours
    public List<Assignment> getAssignmentsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));
        return assignmentRepository.findByCourse(course);
    }

    // Créer un nouveau devoir pour un cours donné
    public Assignment createAssignment(Assignment assignment, Long courseId) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));
        assignment.setCourse(course);
        return assignmentRepository.save(assignment);
    }
}
