package com.ocm.onlinecoursemanagementbackend.model;

import java.time.LocalDateTime;

public record AssignmentDTO(Long id, String title, String description, LocalDateTime deadline, Long courseId) {}
