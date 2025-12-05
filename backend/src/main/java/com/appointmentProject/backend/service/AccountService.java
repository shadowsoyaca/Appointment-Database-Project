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
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // 1. INSERT
    public Account addAccount(Account account) {
        // TODO: add validation/format checks as needed
        return accountRepository.save(account);
    }

    // 2. UPDATE
    public Account updateAccount(Account updated) {
        // Step 1: Ensure the account exists
        Account current = accountRepository.findById(updated.getUsername())
                .orElseThrow(() ->
                        new RecordNotFoundException(
                                "Account with username " + updated.getUsername() + " was not found."
                        )
                );

        // Step 2: Update mutable fields
        current.setPassword(updated.getPassword());
        current.setEmail(updated.getEmail());
        current.setUserType(updated.getUserType());

        // Step 3: Save and return updated account
        return accountRepository.save(current);
    }

    // 3. DELETE
    public void deleteAccountByUsername(String username) {
        Account current = accountRepository.findById(username)
                .orElseThrow(() ->
                        new RecordNotFoundException(
                                "Account with username " + username + " was not found."
                        )
                );
        accountRepository.delete(current);
    }

    // 4. SELECT ALL
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // 5. SELECT BY USERNAME
    public Optional<Account> getByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
