package com.ocm.onlinecoursemanagementbackend.service;

import com.ocm.onlinecoursemanagementbackend.exception.ResourceNotFoundException;
import com.ocm.onlinecoursemanagementbackend.model.*;
import com.ocm.onlinecoursemanagementbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Récupérer un utilisateur par ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    // Créer un nouvel utilisateur
    public User createUser(UserRequest user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ResourceNotFoundException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceNotFoundException("Email already exists");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new ResourceNotFoundException("Passwords do not match");
        }
        if (user.getRole() == Role.STUDENT) {
            Student student = createStudent(user);
            student.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(student);
        } else if (user.getRole() == Role.INSTRUCTOR) {
            Instructor instructor = createInstructor(user);
            instructor.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(instructor);
        } else {
            throw new ResourceNotFoundException("Role not found");
        }
    }

    private Instructor createInstructor(UserRequest user) {
        Instructor instructor = new Instructor();
//        instructor.setFirstName(user.getFirstName());
//        instructor.setLastName(user.getLastName());
//        instructor.setAge(user.getAge());
//        instructor.setGender(user.getGender());
//        instructor.setAddress(user.getAddress());
//        instructor.setCity(user.getCity());
//        instructor.setCountry(user.getCountry());
//        instructor.setPhone(user.getPhone());
//        instructor.setMobile(user.getMobile());
        instructor.setEmail(user.getEmail());
        instructor.setUsername(user.getUsername());
        instructor.setRole(user.getRole());
        return instructor;
    }

    private Student createStudent(UserRequest user) {
        Student student = new Student();
//        student.setFirstName(user.getFirstName());
//        student.setLastName(user.getLastName());
//        student.setAge(user.getAge());
//        student.setGender(user.getGender());
//        student.setAddress(user.getAddress());
//        student.setCity(user.getCity());
//        student.setCountry(user.getCountry());
//        student.setPhone(user.getPhone());
//        student.setMobile(user.getMobile());
        student.setEmail(user.getEmail());
        student.setUsername(user.getUsername());
        student.setRole(user.getRole());
        return student;
    }

    // Supprimer un utilisateur
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

    public String authenticate(String username, String password) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with username " + username));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResourceNotFoundException("Invalid password");
        }

        return "Bearer " + user.getId();
    }
}
