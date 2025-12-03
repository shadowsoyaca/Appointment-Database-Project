/*****************************************************************
 *  UserDTO.java
 *
 *      A model used for storing user account information.
 *      - username: username of the account
 *      - role: String of the type of role the user will have.
 *
 * @author: Matthew Kiyono
 * @version: 1.0
 * @since: 12/1/2025
 ********************************************************************/
package com.appointmentProject.desktop.model;

public class UserDTO {
    private final String username;
    private final String role;

    public UserDTO(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}