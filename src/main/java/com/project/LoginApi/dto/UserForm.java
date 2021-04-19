package com.project.LoginApi.dto;

import com.project.LoginApi.validator.PasswordsEqualConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordsEqualConstraint(message = "passwords are not equal")
public class UserForm {

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String phone;

    @Email(message = "Please enter a valid e-mail address")
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    @Size(min = 6)
    private String confirmPassword;

    @NotBlank
    private String address;
}
