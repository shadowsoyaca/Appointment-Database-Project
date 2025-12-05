package com.appointmentProject.backend.dto;

import com.appointmentProject.backend.model.Account;

public class UserDTO {

    private String username;
    private String email;
    private String role;

    public UserDTO(Account account) {
        this.username = account.getUsername();
        this.email = account.getEmail();
        this.role = account.getUserType().name();
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}
