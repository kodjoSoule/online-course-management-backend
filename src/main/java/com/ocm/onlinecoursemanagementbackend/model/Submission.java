package com.ocm.onlinecoursemanagementbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @Column(nullable = false)
    private String filePath; // Chemin du fichier soumis

    // Getters, Setters, Constructors
}
