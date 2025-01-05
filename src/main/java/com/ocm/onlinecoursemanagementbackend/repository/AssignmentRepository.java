package com.ocm.onlinecoursemanagementbackend.repository;

import com.ocm.onlinecoursemanagementbackend.model.Assignment;
import com.ocm.onlinecoursemanagementbackend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByCourse(Course course);
}
