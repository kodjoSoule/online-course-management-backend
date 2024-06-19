package com.ocm.onlinecoursemanagementbackend.models;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "assignments")
@Data
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    // Constructeurs, getters et setters
}