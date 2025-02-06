package org.app.user.controller;

import lombok.RequiredArgsConstructor;
import org.app.user.event.UserLoginEvent;
import org.app.user.event.UserRegistrationEvent;
import org.app.user.listener.AuthEventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthEventListener userEventListener;
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> signUp(@RequestBody UserRegistrationEvent event) {
        return ResponseEntity.ok(userEventListener.handleUserRegistration(event));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody UserLoginEvent event) {
        return userEventListener.handleUserLogin(event);
    }
}
