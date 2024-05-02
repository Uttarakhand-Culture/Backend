package com.example.uttarakhand.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {


    @Override
    public boolean test(String email) {
        // TODO: REGEX to validate email

        boolean checkForValidEmail = email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
        return checkForValidEmail;
    }
}
