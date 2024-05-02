package com.example.uttarakhand.registration.token;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRespository;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRespository) {
        this.confirmationTokenRespository = confirmationTokenRespository;
    }

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRespository.save(confirmationToken);
    }



    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRespository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRespository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
