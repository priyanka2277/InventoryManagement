package com.example.skgsss.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Phone number must not be blank")
    private String phoneNo;
    @NotBlank(message="password must be not blank")
    private String password;

    private Boolean enabled;

    private Set<String> roles;
}
