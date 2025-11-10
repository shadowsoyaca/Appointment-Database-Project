/******************************************************************************
 * Account.java
 *
 *  *****THE TABLE NAME IT WILL REFERENCE WILL BE CALLED USER_ACCOUNT.
 *
 *     Represents an Account entity from the database in object
 *     format for transferring from the database to the frontend.
 *
 *     Contains the identifying variables of Accounts and is used by the
 *     service layer for retrieval and updates.
 *     - "username": the  unique username of the user.
 *     - "password": the password to access the user's account
 *     - "email": the email that is connected to the user's account
 *     - "user_type": the user's authority level that gives them access to certain
 *              privileges.
 *              Types:
 *                  - Admin
 *                  - Provider
 *                  - Nurse
 *                  - Receptionist
 *
 * @author Matthew Kiyono
 * @version 1.1
 * @since 10/29/2025
 ********************************************************************************/

package com.appointmentProject.backend.model;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "useraccount")
public class Account {

    //User Types
    public enum authorization{
        ADMIN,
        PROVIDER,
        NURSE,
        RECEPTIONIST
    }

    //Variables
        @Id
        @NotNull()
        @Column(name = "username", unique = true, nullable = false)
        private String username;

        @NotNull
        @Column(name = "password", nullable = false)
        private String password;

        @NotNull
        @Column(name = "email", nullable = false, unique = true)
        private String email;

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(name = "user_type", nullable = false)
        private authorization user_type;

    //Constructor
    public Account(String username, String password, String email, authorization user_type){
        this.username = username;
        this.password = password;
        this.email = email;
        this.user_type = user_type;
    }

    //getters
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getEmail() {return email;}
    public authorization getUser_type() {return user_type;}

    //setters
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setEmail(String email) {this.email = email;}
    public void setUser_type(authorization user_type) {this.user_type = user_type;}

    //toString
    @Override
    public String toString() {
        return "User Account: " +
                "\nUsername: " + this.username +
                "\nPassword: " + this.password +
                "\nEmail: " + this.email +
                "\nUserType: " + this.user_type +
                "\n";
    }
}
