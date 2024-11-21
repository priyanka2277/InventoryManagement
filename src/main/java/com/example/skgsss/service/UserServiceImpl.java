package com.example.skgsss.service;
import com.example.skgsss.Repository.RoleRepository;
import com.example.skgsss.Repository.UserRepo;
import com.example.skgsss.dto.UserDto;
import com.example.skgsss.entity.Role;
import com.example.skgsss.entity.User;
import com.example.skgsss.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto registerUser(UserDto userDto) {
        // Check if user with the same email already exists
        if (userRepo.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with email " + userDto.getEmail() + " already exists");
        }

        // Encrypt the password
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Map DTO to Entity
        User user = modelMapper.map(userDto, User.class);

        // Assign roles from the userDto
        Set<Role> roles = new HashSet<>();
        for (String roleName : userDto.getRoles()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new IllegalArgumentException("Role " + roleName + " not found"));
            roles.add(role);
        }

        // Set roles to user
        user.setRoles(roles);

        // Save user entity
        User savedUser = userRepo.save(user);

        // Map Entity back to DTO and return
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        // Fetch the existing user from the database
        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        // Authenticate the current user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> currentUserRoles = auth.getAuthorities();

        // Only allow admins or the user themselves to update
        boolean isAdmin = currentUserRoles.stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        String currentUserEmail = auth.getName();

        if (!isAdmin && !existingUser.getEmail().equals(currentUserEmail)) {
            throw new SecurityException("You are not authorized to update this user.");
        }

        // Update the user fields
        if (userDto.getFirstName() != null) {
            existingUser.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null) {
            existingUser.setLastName(userDto.getLastName());
        }
        if (userDto.getEmail() != null) {
            existingUser.setEmail(userDto.getEmail());
        }
        if (userDto.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        // Update roles if provided
        if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            for (String roleName : userDto.getRoles()) {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new IllegalArgumentException("Role " + roleName + " not found"));
                roles.add(role);
            }
            existingUser.setRoles(roles);
        }

        // Save updated user and return the updated DTO
        User updatedUser = userRepo.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> currentUserRoles = auth.getAuthorities();

        boolean isAdmin = currentUserRoles.stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        String currentUserEmail = auth.getName();

        if (!isAdmin && !existingUser.getEmail().equals(currentUserEmail)) {
            throw new SecurityException("You are not authorized to delete this user.");
        }

        userRepo.delete(existingUser);
    }
}
