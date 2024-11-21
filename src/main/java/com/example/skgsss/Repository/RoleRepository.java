package com.example.skgsss.Repository;

import com.example.skgsss.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Custom query method to find a role by its name
    Optional<Role> findByName(String name);
    boolean existsByName(String name);
}
