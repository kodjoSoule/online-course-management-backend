package com.ocm.onlinecoursemanagementbackend.service;
import com.ocm.onlinecoursemanagementbackend.exception.ResourceNotFoundException;
import com.ocm.onlinecoursemanagementbackend.model.Course;
import com.ocm.onlinecoursemanagementbackend.model.User;
import com.ocm.onlinecoursemanagementbackend.repository.CourseRepository;
import com.ocm.onlinecoursemanagementbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    // Récupérer tous les cours
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Récupérer un cours par ID
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
    }

    // Créer un nouveau cours
    public Course createCourse(Course course, Long instructorId) {
        User instructor = userRepository.findById(instructorId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id " + instructorId));
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    // Supprimer un cours
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with id " + id);
        }
        courseRepository.deleteById(id);
    }
}
