package com.ocm.onlinecoursemanagementbackend.models;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class CustomUser {
    private static final Logger logger = LoggerFactory.getLogger(CustomUser.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<String> roles;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @CreationTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    @CreationTimestamp
    @Column(name = "last_login")
    private Date lastLogin;
    @CreationTimestamp
    @Column(name = "last_logout")
    private Date lastLogout;
    @Column(name = "is_active")
    private boolean isActive = false ;
    @Column(name = "is_deleted")
    private boolean isDeleted = false ;
    @Column(name = "is_locked", nullable = false)
    private boolean isLocked = false;
    @Column(name = "is_expired")
    private boolean isExpired = false;

    public CustomUser(String username, String password, List<GrantedAuthority> grantedAuthorities) {
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            roles.add(grantedAuthority.getAuthority());
        }
    }

    public CustomUser() {

    }


    // Méthodes utilitaires pour les rôles



    public String getRole() {
        return roles.get(0);
    }

    public void addRole(String admin) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        if (!roles.contains(admin)) {
            logger.info("Role " + admin + " added to user " + this.username);
            roles.add(admin);
        }
    }
    public void removeRole(String role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        if (roles.contains(role)) {
            logger.info("Role " + role + " removed from user " + this.username);
            roles.remove(role);
        }
    }
}