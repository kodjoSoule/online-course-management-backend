package com.ocm.onlinecoursemanagementbackend.service;
import com.ocm.onlinecoursemanagementbackend.exception.ResourceNotFoundException;
import com.ocm.onlinecoursemanagementbackend.model.Course;
import com.ocm.onlinecoursemanagementbackend.model.Enrollment;
import com.ocm.onlinecoursemanagementbackend.model.User;
import com.ocm.onlinecoursemanagementbackend.repository.CourseRepository;
import com.ocm.onlinecoursemanagementbackend.repository.EnrollmentRepository;
import com.ocm.onlinecoursemanagementbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             CourseRepository courseRepository,
                             UserRepository userRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    // Inscrire un étudiant à un cours
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        User student = userRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return enrollmentRepository.save(enrollment);
    }
}
