package com.example.skgsss.controller;

import com.example.skgsss.dto.UserDto;
import com.example.skgsss.entity.User;
import com.example.skgsss.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    public UserControllerTest(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void registerUser_Success(){
        UserDto userDto=new UserDto(1L,"kusum","pandey","kusumpandey@gmail.com","9848794704","eng*187#",true, Set.of("USER"));

        when(userService.registerUser(userDto)).thenReturn(userDto);

        ResponseEntity<?> response =userController.registerUser(userDto);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals("User 'kusumpandey@gmail.com' registered successfully with Id :1",response.getBody());
        verify(userService,times(1)).registerUser(userDto);
    }
    @Test
    void updateUser_Success(){
        Long id=1L;
        UserDto userDto=new UserDto(id,"Elisha","Karki","elishakarki2277@gmail.com","9749534828","phy#",true,Set.of("SUPPLIER"));
        when(userService.updateUser(id,userDto)).thenReturn(userDto);
        ResponseEntity<?> response=userController.updateUser(id,userDto);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("User with ID:1 updated successfully.New username:'elishakarki2277@gmail.com'",response.getBody());
        verify(userService,times(1)).updateUser(id,userDto);
    }
    @Test
    void getUserById_Success(){
        Long id=1L;
        UserDto userDto=new UserDto(id,"Elisha","Karki","elishakarki2277@gmail.com","9749534828","phy#",true,Set.of("SUPPLIER"));
        when(userService.getUserById(id)).thenReturn(userDto);
        ResponseEntity<?> response=userController.getUserById(id);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(userDto,response.getBody());
        verify(userService,times(1)).getUserById(id);
    }
    @Test
    void getAllUsers_Success(){
        List<UserDto> users= Arrays.asList(
                new UserDto(1L,"Priyanka","Pandey","priyankapandet2277@gmail.com","984723628","sp*187#",true,Set.of("ADMIN")),
                new UserDto(2L,"Skg","pandey","skgpandey2277@gmail.com","837417738","en*187",true,Set.of("ADMIN"))
        );
        when(userService.getAllUsers()).thenReturn(users);
        ResponseEntity<List<UserDto>> response=userController.getAllUsers();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(users,response.getBody());
        verify(userService,times(1)).getAllUsers();
    }
    @Test
    void deleteUser_Success(){
        Long id=1L;
        doNothing().when(userService).deleteUser(id);
        ResponseEntity<String> response=userController.deleteUser(id);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("User with ID:1 deleted successfully.",response.getBody());
        verify(userService,times(1)).deleteUser(id);
    }
}
