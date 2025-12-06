package com.appointmentProject.backend.service;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.Account;
import com.appointmentProject.backend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/******************************************************************************************************************
 * AccountService.java
 *
 *      This service class calls on the AccountRepository interface to communicate with the database.
 *
 *      The types of queries that can be made:
 *      - Insert
 *      - Delete
 *      - Update
 *      - Select (by: all, username)
 *
 *      Later updates can include format checks (valid email, password constraints, etc.).
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 12/03/2025
 ******************************************************************************************************************/

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.Account;
import com.appointmentProject.backend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accRepo;

    // CREATE
    public Account addAccount(Account account) {
        return accRepo.save(account);
    }

    // READ ALL
    public List<Account> getAllAccounts() {
        return accRepo.findAll();
    }

    // READ ONE
    public Optional<Account> getByUsername(String username) {
        return accRepo.findByUsername(username);
    }

    // LOGIN LOOKUP
    public Account findByUsernameAndPassword(String username, String password) {
        return accRepo.findByUsernameAndPassword(username, password);
    }


    // UPDATE
    public Account updateAccount(Account account) {
        Optional<Account> existing = accRepo.findByUsername(account.getUsername());

        if (existing.isEmpty()) {
            throw new RecordNotFoundException("Account with username " + account.getUsername() + " does not exist.");
        }

        return accRepo.save(account);
    }

    // DELETE
    public void deleteAccountByUsername(String username) {
        if (!accRepo.existsById(username)) {
            throw new RecordNotFoundException("Cannot delete. No account found with username " + username);
        }
        accRepo.deleteById(username);
    }
}
