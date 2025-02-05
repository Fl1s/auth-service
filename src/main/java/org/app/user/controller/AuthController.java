package org.app.user.controller;

import lombok.RequiredArgsConstructor;
import org.app.user.event.UserLoginEvent;
import org.app.user.event.UserRegisteredEvent;
import org.app.user.listener.AuthEventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthEventListener userEventListener;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(@RequestBody UserRegisteredEvent event) {
        return ResponseEntity.ok(userEventListener.handleUserRegistration(event));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginEvent event) {
        return userEventListener.handleUserLogin(event);
    }
}
