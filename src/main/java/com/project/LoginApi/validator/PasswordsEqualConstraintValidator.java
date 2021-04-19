package com.project.LoginApi.validator;

import com.project.LoginApi.dto.UserForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsEqualConstraintValidator implements
        ConstraintValidator<PasswordsEqualConstraint, UserForm> {

    @Override
    public void initialize(PasswordsEqualConstraint arg0) {
    }

    @Override
    public boolean isValid(UserForm userForm, ConstraintValidatorContext arg1) {
        return userForm.getPassword().equals(userForm.getConfirmPassword());
    }
}
