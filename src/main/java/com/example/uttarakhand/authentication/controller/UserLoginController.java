package com.example.uttarakhand.authentication.controller;

import com.example.uttarakhand.authentication.LoginRequest;
import com.example.uttarakhand.authentication.service.LoginMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.uttarakhand.authentication.service.LoginService;

import javax.naming.Context;
import java.security.Principal;

@RestController
@CrossOrigin("*")
public class UserLoginController {

    private final LoginService loginService;
    public UserLoginController(LoginService loginService) {
        this.loginService = loginService;
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
//        if (request.getEmail().trim().isEmpty() || request.getPassword().trim().isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fields should not be empty");
//        }
//        try {
//            String token = loginService.authenticate(request);
//            return ResponseEntity.ok(token);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        if (request.getEmail().trim().isEmpty() || request.getPassword().trim().isEmpty()) {
            return ResponseEntity.ok(new LoginMessage("Fields should not be empty", false));
        }
        LoginMessage  loginMessage = loginService.loginUser(request);
        return ResponseEntity.ok(loginMessage);
    }

    @GetMapping(path = "/home")
    public String home() {
        return "This is Home Page";
    }

    @GetMapping(path = "/profile")
    public String profile(Principal principal) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(principal.getName());
        return "This is profile Page"+ principal.getName();
    }

    @GetMapping(path = "/admin")
    public ResponseEntity<?> admin(@RequestBody LoginRequest request) {
        if (request.getEmail().trim().isEmpty() || request.getPassword().trim().isEmpty()) {
            return ResponseEntity.ok(new LoginMessage("Fields should not be empty", false));
        }
        LoginMessage loginMessage = loginService.loginUser(request);
        return ResponseEntity.ok(loginMessage);
    }
}
