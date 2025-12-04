package com.appointmentProject.backend.service;

import com.appointmentProject.backend.dto.LoginRequest;
import com.appointmentProject.backend.model.Account;
import com.appointmentProject.backend.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AccountRepository repo;

    public AuthService(AccountRepository repo) {
        this.repo = repo;
    }

    public Account login(LoginRequest request) {

        Account account = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!account.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return account;
    }
}
