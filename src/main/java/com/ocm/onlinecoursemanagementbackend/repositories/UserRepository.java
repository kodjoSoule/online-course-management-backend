package com.ocm.onlinecoursemanagementbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ocm.onlinecoursemanagementbackend.models.CustomUser;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long>{
    CustomUser findByUsername(String username);
}
