package com.ocm.onlinecoursemanagementbackend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "instructors")
public class Instructor extends User {
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Course> courses; // Liste des cours créés par l'instructeur

    public Instructor() {
        super();
        this.setRole(Role.INSTRUCTOR);
    }

    // Méthodes spécifiques à l'instructeur
}
