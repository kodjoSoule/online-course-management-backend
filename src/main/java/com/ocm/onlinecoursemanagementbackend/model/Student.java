package com.ocm.onlinecoursemanagementbackend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "students")
public class Student extends User {
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments; // Liste des inscriptions aux cours

    public Student() {
        super();
        this.setRole(Role.STUDENT);
    }

    // Méthodes spécifiques à l'étudiant

}
