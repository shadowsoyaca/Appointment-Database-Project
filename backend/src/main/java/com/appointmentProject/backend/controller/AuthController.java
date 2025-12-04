package com.appointmentProject.backend.controller;

import com.appointmentProject.backend.dto.LoginRequest;
import com.appointmentProject.backend.dto.UserDTO;
import com.appointmentProject.backend.model.Account;
import com.appointmentProject.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequest request) {
        Account account = service.login(request);
        return ResponseEntity.ok(new UserDTO(account));
    }
}
