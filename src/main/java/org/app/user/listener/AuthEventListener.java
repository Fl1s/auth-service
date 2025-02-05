package org.app.user.listener;

import lombok.RequiredArgsConstructor;
import org.app.user.event.UserLoginEvent;
import org.app.user.event.UserRegisteredEvent;
import org.app.user.service.AuthService;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthEventListener {

    private final KafkaTemplate<String, UserRegisteredEvent> userRegisteredTemplate;
    private final AuthService authService;

    @KafkaListener(topics = "user-registration", groupId = "auth-service")
    public boolean handleUserRegistration(UserRegisteredEvent event) {
        try {
            authService.registerUser(event.getUsername(), event.getEmail(), event.getPassword());
            userRegisteredTemplate.send("user-registered", event);
            return ResponseEntity.ok("[User successfully registered!]").hasBody();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("[User registration failed...]").hasBody();
        }
    }

    @KafkaListener(topics = "user-login", groupId = "auth-service")
    public ResponseEntity<?> handleUserLogin(UserLoginEvent event) {
        try {
            AccessTokenResponse tokenResponse = authService.authenticateUser(event.getUsername(), event.getPassword());
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}
