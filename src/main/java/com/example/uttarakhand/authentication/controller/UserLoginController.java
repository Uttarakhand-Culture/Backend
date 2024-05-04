package com.example.uttarakhand.authentication.controller;

import com.example.uttarakhand.authentication.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.uttarakhand.authentication.service.LoginService;

@RestController
public class UserLoginController {

    private final LoginService loginService;
    public UserLoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        if( request.getUsername().trim().isEmpty() || request.getPassword().trim().isEmpty())
            return "Fields should not be empty";

        return loginService.request(request);
    }
}
