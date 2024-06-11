package com.example.uttarakhand.authentication.controller;

import org.springframework.web.bind.annotation.*;
import com.example.uttarakhand.authentication.RegistrationRequest;
import com.example.uttarakhand.authentication.service.RegistrationService;

@RestController
@CrossOrigin
public class UserRegistrationController {

    private final RegistrationService registrationService;
    public UserRegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(path = "/signup")
    public String register (@RequestBody RegistrationRequest request){

        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println("Registration request: " + request);
        System.out.println("\n\n\n\n\n\n\n\n");

        if(request.getFirstName().trim().isEmpty() || request.getPassword().trim().isEmpty() || request.getEmail().trim().isEmpty()){
//            throw new IllegalStateException("Fields should not be empty");
            return "Fields should not be empty!";
        }
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}

/**
 * For sending http status code
 *  public ResponseEntity<Void> addBook(){
 *             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
 *     }
 * */

