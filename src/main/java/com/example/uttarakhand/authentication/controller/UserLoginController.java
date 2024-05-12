package com.example.uttarakhand.authentication.controller;

import com.example.uttarakhand.authentication.LoginRequest;
import com.example.uttarakhand.authentication.service.LoginMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.uttarakhand.authentication.service.LoginService;

@RestController
@CrossOrigin(origins = "*")
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
    public ResponseEntity<?> loginEmployee(@RequestBody LoginRequest request)
    {
        LoginMessage loginMessage = loginService.loginUser(request);
        return ResponseEntity.ok(loginMessage);
    }
}
