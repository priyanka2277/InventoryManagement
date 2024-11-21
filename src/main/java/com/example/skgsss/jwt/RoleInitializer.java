package com.example.skgsss.jwt;

import com.example.skgsss.Repository.RoleRepository;
import com.example.skgsss.entity.Role;
import com.example.skgsss.enums.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void run(String... args){
        createRoleIfNotExists(RoleName.ROLE_ADMIN);
        createRoleIfNotExists(RoleName.ROLE_USER);
        createRoleIfNotExists(RoleName.ROLE_SUPPLIER);
        createRoleIfNotExists(RoleName.ROLE_CUSTOMER);
    }
    private void createRoleIfNotExists(RoleName roleName){
        if(!roleRepository.existsByName(roleName.toString())){
            Role role=new Role();
            role.setName(roleName.toString());
            roleRepository.save(role);
        }
    }
}
