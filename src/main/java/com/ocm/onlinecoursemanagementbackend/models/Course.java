package com.ocm.onlinecoursemanagementbackend.models;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    @ManyToMany
    @JoinTable(
            name = "course_enrollments",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> enrolledStudents = new HashSet<>();

    // Constructeurs, getters et setters

    // Méthodes utilitaires pour les étudiants inscrits
    public void enrollStudent(User student) {
        enrolledStudents.add(student);
    }

    public void removeStudent(User student) {
        enrolledStudents.remove(student);
    }
}