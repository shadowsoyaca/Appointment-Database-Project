package com.appointmentProject.backend.repository;

import com.appointmentProject.backend.model.Account;
import com.appointmentProject.backend.model.Account.authorization;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*************************************************************************************************
 *  AccountRepositoryTest.java
 *
 *      The purpose of this test is to ensure that the AccountRepository executes queries
 *      correctly. @Autowired injects the repository and EntityManager managed by Spring.
 *
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 12/03/2025
 *************************************************************************************************/
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    @Transactional
    void resetDatabase() {
        entityManager.createQuery("DELETE FROM Account").executeUpdate();
    }

    @Test
    void testSaveAndFindByUsername() {
        // 1. Create entity
        Account a = new Account("userRepo", "pw", "repo@mail.com", authorization.ADMIN);

        // 2. Save entity
        accountRepository.save(a);

        // 3. Call repository method
        Optional<Account> result = accountRepository.findByUsername("userRepo");

        // 4. Assert
        assertTrue(result.isPresent());
        assertEquals("repo@mail.com", result.get().getEmail());
    }

    @Test
    void testFindAll() {
        Account a = new Account("user1", "pw", "u1@mail.com", authorization.ADMIN);
        Account b = new Account("user2", "pw", "u2@mail.com", authorization.PROVIDER);

        accountRepository.save(a);
        accountRepository.save(b);

        List<Account> results = accountRepository.findAll();

        assertNotNull(results);
        assertTrue(results.size() >= 2);
    }
}
