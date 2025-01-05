package com.ocm.onlinecoursemanagementbackend.repository;

import com.ocm.onlinecoursemanagementbackend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>{
}
