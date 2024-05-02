package com.example.uttarakhand.registration;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@CrossOrigin(origins = "*")
public class UserRegistrationController {

    private final RegistrationService registrationService;

    public UserRegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public String register (@RequestBody RegistrationRequest request){

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Registration request: " + request);

        if(request.getFirstName().trim().isEmpty() ||
           request.getLastName().trim().isEmpty() ||
           request.getPassword().trim().isEmpty() ||
           request.getEmail().trim().isEmpty()
        ){
            throw new IllegalStateException("Fields should not be empty");
        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
