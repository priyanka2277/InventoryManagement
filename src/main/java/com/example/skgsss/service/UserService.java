package com.example.skgsss.service;

import com.example.skgsss.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto userDto);
    UserDto updateUser(Long userId,UserDto userDto);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    void deleteUser(Long userId);
}
