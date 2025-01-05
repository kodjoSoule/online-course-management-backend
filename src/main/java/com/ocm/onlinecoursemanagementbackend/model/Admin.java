package com.ocm.onlinecoursemanagementbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends User {
    // Attributs spécifiques à l'admin (si nécessaire)

    public Admin() {
        super();
        this.setRole(Role.ADMIN);
    }

    // Méthodes spécifiques à l'admin
}
