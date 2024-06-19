package com.ocm.onlinecoursemanagementbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ocm.onlinecoursemanagementbackend.models.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
