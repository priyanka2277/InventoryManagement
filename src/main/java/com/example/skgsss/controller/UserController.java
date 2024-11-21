package com.example.skgsss.controller;

import com.example.skgsss.dto.UserDto;
import com.example.skgsss.entity.User;
import com.example.skgsss.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        UserDto registeredUser=userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("User '%s' registered successfully with Id :%d",registeredUser.getEmail(),registeredUser.getId()));
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId,@RequestBody UserDto userDto){
        UserDto updatedUser=userService.updateUser(userId,userDto);
        return ResponseEntity.ok(String.format("User with ID:%d updated successfully.New username:'%s'",updatedUser.getId(),updatedUser.getEmail()));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        try {
            UserDto userDto = userService.getUserById(userId);
            return ResponseEntity.ok(userDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    String.format("User not found with ID:%d .Please check the ID AND try again.", userId)
            );

        }
    }
        @GetMapping("/all")
         public ResponseEntity<List<UserDto>>  getAllUsers(){
            List<UserDto> users=userService.getAllUsers();
            return ResponseEntity.ok(users);
        }
        @DeleteMapping("/delete/{userId}")
        public ResponseEntity<String> deleteUser(@PathVariable Long userId){
            try{
                userService.deleteUser(userId);
                return ResponseEntity.ok(String.format("User with ID:%d deleted successfully.",userId));

            }catch(EntityNotFoundException e){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        String.format("User not found with ID:%d.Unable to delete non-existent user.",userId));

            }

        }




}
