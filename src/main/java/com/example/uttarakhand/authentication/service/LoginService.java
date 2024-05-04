package com.example.uttarakhand.authentication.service;

import org.springframework.stereotype.Service;
import com.example.uttarakhand.user.UserRepository;
import com.example.uttarakhand.authentication.LoginRequest;

@Service
public class LoginService {

    private final UserRepository userRepository;
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String request(LoginRequest request) {
        boolean isFound = userRepository.findByEmail(request.getUsername()).isPresent();
//        boolean isMatch = userRepository.getPasswordWhereEmail(request.getUsername()).equals(request.getPassword());

        if (!(isFound)) return "User not found";

        return "Login successfully";
    }
}
