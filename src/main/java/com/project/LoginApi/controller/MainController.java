package com.project.LoginApi.controller;

import com.project.LoginApi.dao.UserDao;
import com.project.LoginApi.dto.UserForm;
import com.project.LoginApi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@RestController
public class MainController {

    @Autowired
    private Validator validator;
    @Autowired
    private UserDao userDao;

    @GetMapping("/")
    public String home() {
        return "Welcome Homepage!!";
    }

    @GetMapping("/members")
    public List<User> viewMembers() {
        List<User> listUser = userDao.getAllUsers();
        return listUser;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> saveRegister(@Valid @RequestBody UserForm userForm) {
        ResponseEntity responseEntity = null;
        try {
            Set<ConstraintViolation<UserForm>> failures = validator
                    .validate(userForm);

            if (!failures.isEmpty()) {
                ResponseEntity.badRequest();

            } else {
                userDao.saveUser(userForm);
            }
            responseEntity = ResponseEntity.ok("User is valid");
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body("user is available");
        }
        return responseEntity;
    }
}
