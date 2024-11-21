package com.example.skgsss.service;

import com.example.skgsss.Repository.RoleRepository;
import com.example.skgsss.Repository.UserRepo;
import com.example.skgsss.dto.UserDto;
import com.example.skgsss.entity.Role;
import com.example.skgsss.entity.User;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepo userRepo;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private SecurityContext securityContext;
    @Mock
    private Authentication authentication;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
    }
    @Test
    void registerUser_Success(){
        UserDto userDto=new UserDto(1L,"John","Doe","johndoe2277@gmail.com","2374893","p367s",true, Set.of("ROLE_ADMIN"));
        User user=new User();
        Role role=new  Role(1L,"ROLE_ADMIN");
        when(userRepo.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.of(role));
        when(modelMapper.map(userDto,User.class)).thenReturn(user);
        when(userRepo.save(user)).thenReturn(user);
        when(modelMapper.map(user,UserDto.class)).thenReturn(userDto);
        UserDto result=userService.registerUser(userDto);
        assertEquals(userDto,result);
        verify(userRepo).findByEmail(userDto.getEmail());
        verify(passwordEncoder).encode("p367s");
        verify(roleRepository).findByName("ROLE_ADMIN");
        verify(userRepo).save(user);

    }
    @Test
    void getUserById_Success(){
        Long id=1L;
        User user=new User();
        UserDto userDto=new UserDto(id,"kusum","pandey","kusumpandey2277@gmail.com","9848794573","psk",true,Set.of("ROLE_USER"));
        when(userRepo.findById(id)).thenReturn(Optional.of(user));
        when(modelMapper.map(user,UserDto.class)).thenReturn(userDto);
        UserDto result=userService.getUserById(id);
        assertEquals(userDto,result);
        verify(userRepo).findById(id);
    }

}
