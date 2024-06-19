package com.ocm.onlinecoursemanagementbackend.repositories;

import com.ocm.onlinecoursemanagementbackend.models.Assignment;
import com.ocm.onlinecoursemanagementbackend.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long>{
    Assignment save(Assignment assignment);
    Assignment findById(int id);
    Assignment findByCourse(Course course);
}
