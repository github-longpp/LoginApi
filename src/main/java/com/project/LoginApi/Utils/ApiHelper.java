package com.project.LoginApi.Utils;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class ApiHelper {
    private Map<String, String> generateObjectErrors(BindingResult result) {
        Map<String, String> fieldErrorMap = new HashMap<>();
        result.getGlobalErrors().forEach(objectError -> {
            if (objectError.getCode().contains("ConfirmPassword")) {
                fieldErrorMap.put("confirmPassword", objectError.getDefaultMessage());
            }
        });
        return fieldErrorMap;
    }
}
