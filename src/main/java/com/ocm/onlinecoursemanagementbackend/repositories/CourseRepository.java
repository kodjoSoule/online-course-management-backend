package com.ocm.onlinecoursemanagementbackend.repositories;

import com.ocm.onlinecoursemanagementbackend.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
}
