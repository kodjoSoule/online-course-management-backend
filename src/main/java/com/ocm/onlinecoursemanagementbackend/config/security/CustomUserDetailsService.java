package com.ocm.onlinecoursemanagementbackend.config.security;



import com.ocm.onlinecoursemanagementbackend.models.CustomUser;
import com.ocm.onlinecoursemanagementbackend.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser user = userRepository.findByUsername(username);
        return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRole()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
    //create new user

    public void createUserIfNeeded(
            CustomUser dbUser
    ) {
        // Check if user already exists
        if (userRepository.findByUsername("admin") != null){
            // If user does not exist, create it
            userRepository.save(dbUser);
        }
    }


    @PostConstruct
    public void createUserIfNeeded() {
        String defaultAdminUsername = "Mohamed";
        CustomUser existingUser = userRepository.findByUsername(defaultAdminUsername);
        if (existingUser == null) {
            CustomUser adminUser = new CustomUser();
            adminUser.setEmail("Mohamed@gmail.com");
            adminUser.setFirstName("Mohamed");
            adminUser.setLastName("Ben");
            adminUser.setUsername(defaultAdminUsername);
            adminUser.setPassword(passwordEncoder.encode("Passer123")); // replace "adminPassword" with the actual password
            adminUser.addRole("ADMIN");
            userRepository.save(adminUser);
        }
    }

    public void createUtlisateur(CustomUser utilisateur){
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        userRepository.save(utilisateur);
    }
}
