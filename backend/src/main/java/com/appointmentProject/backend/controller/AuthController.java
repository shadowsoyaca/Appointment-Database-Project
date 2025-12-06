package com.appointmentProject.backend.controller;

import com.appointmentProject.backend.model.Account;
import com.appointmentProject.backend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.appointmentProject.backend.model.Account;
import com.appointmentProject.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AccountService accService;

    @GetMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {

        // Try to find a matching account
        Account acc = accService.findByUsernameAndPassword(username, password);

        if (acc == null) {
            return "INVALID";
        }

        // Return the userType enum as a string
        return acc.getUserType().toString();
    }
}
