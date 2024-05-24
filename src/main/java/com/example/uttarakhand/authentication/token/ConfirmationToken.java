package com.example.uttarakhand.authentication.token;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.example.uttarakhand.user.User;

@Entity
public class ConfirmationToken {

    @Id
    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )
    private Long tokenId;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private User user;


    public Long getTokenId(){
        return tokenId;
    }
    public String getToken() {
        return token;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public User getUser() {
        return user;
    }
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }


    public void setUser(User user) {
        this.user = user;
    }
    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setExpiresAt(LocalDateTime expiredAt) {
        this.expiresAt = expiredAt;
    }
    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }


    public ConfirmationToken() {
    }

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt,  User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
