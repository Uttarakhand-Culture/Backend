package com.example.uttarakhand.user;

import java.util.UUID;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.uttarakhand.authentication.token.ConfirmationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.uttarakhand.authentication.token.ConfirmationTokenService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ConfirmationTokenService confirmationTokenService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, username))
                );

    }

    public String signUpUser(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        String token = UUID.randomUUID().toString();

        // TODO: Send Confirmation Token
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        // TODO: SEND EMAIL
        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
}