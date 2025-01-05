package com.ocm.onlinecoursemanagementbackend.repository;

import com.ocm.onlinecoursemanagementbackend.model.Course;
import com.ocm.onlinecoursemanagementbackend.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByCourse(Course course);
}
