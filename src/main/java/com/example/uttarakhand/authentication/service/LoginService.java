package com.example.uttarakhand.authentication.service;

import com.example.uttarakhand.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.uttarakhand.user.UserRepository;
import com.example.uttarakhand.authentication.LoginRequest;
import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginMessage  loginUser(LoginRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail()); ;

        if (user.isPresent()) {
            String password = request.getPassword();
            String encodedPassword = user.get().getPassword();

            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            if (isPwdRight) {
                Optional<User> userCheck = userRepository.findOneByEmailAndPassword(request.getEmail(), encodedPassword);
                if (userCheck.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("Email not exits", false);
        }
    }
}
