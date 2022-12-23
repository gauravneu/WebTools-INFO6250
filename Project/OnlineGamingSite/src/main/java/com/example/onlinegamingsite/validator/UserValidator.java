package com.example.onlinegamingsite.validator;

import com.example.onlinegamingsite.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "Error FirstName", "First name can't be empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "Error Password", "Password can't be empty");

    }
}
