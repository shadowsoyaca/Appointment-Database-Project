package com.appointmentProject.backend.controller;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.Account;
import com.appointmentProject.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/********************************************************************************************************
 * AccountController.java
 *
 *      This controller defines the API endpoints that the frontend (JavaFX client, or any HTTP client)
 *      will call for CRUD operations on Accounts.
 *
 *      Responsibilities:
 *          - Accept HTTP requests
 *          - Delegate the business logic to AccountService
 *          - Return the service’s results as HTTP responses
 *
 * @author Matthew Kiyono
 * @since 12/3/2025
 * @version 1.0
 ********************************************************************************************************/
@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    AccountService accService;

    // 1. Create Account
    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        Account created = accService.addAccount(account);
        return ResponseEntity.ok(created);
    }

    // 2. Get All Accounts
    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAll() {
        List<Account> list = accService.getAllAccounts();
        return ResponseEntity.ok(list);
    }

    // 3. Get By Username
    @GetMapping("/{username}")
    public ResponseEntity<Account> getByUsername(@PathVariable String username) {
        return accService.getByUsername(username)
                .map(ResponseEntity::ok)
                .orElseThrow(() ->
                        new RecordNotFoundException(
                                "Account with username " + username + " was not found."
                        )
                );
    }

    // 4. Update Account
    @PutMapping("/update")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        Account updated = accService.updateAccount(account);
        return ResponseEntity.ok(updated);
    }

    // 5. Delete Account
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteAccount(@PathVariable String username) {
        accService.deleteAccountByUsername(username);
        return ResponseEntity.ok("Account removed successfully.");
    }
}
