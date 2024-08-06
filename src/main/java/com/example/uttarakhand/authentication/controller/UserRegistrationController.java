package com.example.uttarakhand.authentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.uttarakhand.authentication.RegistrationRequest;
import com.example.uttarakhand.authentication.service.RegistrationService;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class UserRegistrationController {

    private final RegistrationService registrationService;
    public UserRegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegistrationRequest request) {
        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println("Registration request: " + request);
        System.out.println("\n\n\n\n\n\n\n\n");

        Map<String, String> response = new HashMap<>();

        if (request.getFirstName().trim().isEmpty() || request.getPassword().trim().isEmpty() || request.getEmail().trim().isEmpty()) {
            response.put("message", "Fields should not be empty!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        String registrationMessage = registrationService.register(request);
        response.put("message", registrationMessage);

        if (registrationMessage.startsWith("User with email")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }


    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
