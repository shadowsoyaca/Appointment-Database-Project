package com.appointmentProject.backend.controller;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.Account;
import com.appointmentProject.backend.model.Account.authorization;
import com.appointmentProject.backend.service.AccountService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/***************************************************************************************
 *   AccountControllerUnitTest.java
 *
 *      A unit test determining if the AccountController is functioning correctly.
 *
 *      These are TRUE unit tests because the AccountService is mocked — no database calls.
 *
 * @author Matthew
 * @version 1.0
 * @since 12/03/2025
 ***************************************************************************************/
public class AccountControllerUnitTest {

    private AccountController controller;
    private AccountService mockService;

    @BeforeEach
    void setup() {
        mockService = Mockito.mock(AccountService.class);
        controller = new AccountController();
        // manually inject the mock service
        controller.accService = mockService;
    }

    @Test
    void testAddAccount() {
        Account input = new Account("user1", "pass", "u1@mail.com", authorization.ADMIN);
        Account saved = new Account("user1", "pass", "u1@mail.com", authorization.ADMIN);

        when(mockService.addAccount(any(Account.class))).thenReturn(saved);

        ResponseEntity<Account> response = controller.addAccount(input);

        assertEquals("user1", response.getBody().getUsername());
        verify(mockService, times(1)).addAccount(any(Account.class));
    }

    @Test
    void testGetAllAccounts() {
        List<Account> list = List.of(
                new Account("userA", "pw", "a@mail.com", authorization.ADMIN),
                new Account("userB", "pw", "b@mail.com", authorization.PROVIDER)
        );

        when(mockService.getAllAccounts()).thenReturn(list);

        ResponseEntity<List<Account>> response = controller.getAll();

        assertEquals(2, response.getBody().size());
        verify(mockService, times(1)).getAllAccounts();
    }

    @Test
    void testGetByUsername_Found() {
        Account acc = new Account("userX", "pw", "x@mail.com", authorization.NURSE);

        when(mockService.getByUsername("userX")).thenReturn(Optional.of(acc));

        ResponseEntity<Account> response = controller.getByUsername("userX");

        assertEquals("userX", response.getBody().getUsername());
        verify(mockService, times(1)).getByUsername("userX");
    }

    @Test
    void testGetByUsername_NotFound() {
        when(mockService.getByUsername("missingUser")).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class,
                () -> controller.getByUsername("missingUser"));
    }

    @Test
    void testUpdateAccount() {
        Account original = new Account("userY", "oldPw", "old@mail.com", authorization.RECEPTIONIST);
        Account updated = new Account("userY", "newPw", "new@mail.com", authorization.RECEPTIONIST);

        when(mockService.updateAccount(any(Account.class))).thenReturn(updated);

        ResponseEntity<Account> response = controller.updateAccount(original);

        assertEquals("new@mail.com", response.getBody().getEmail());
        verify(mockService, times(1)).updateAccount(any(Account.class));
    }

    @Test
    void testDeleteAccount() {
        doNothing().when(mockService).deleteAccountByUsername("userZ");

        ResponseEntity<String> response = controller.deleteAccount("userZ");

        assertEquals("Account removed successfully.", response.getBody());
        verify(mockService, times(1)).deleteAccountByUsername("userZ");
    }

    @Test
    void testDeleteAccount_NotFound() {
        doThrow(new RecordNotFoundException("not found"))
                .when(mockService).deleteAccountByUsername("badUser");

        assertThrows(RecordNotFoundException.class,
                () -> controller.deleteAccount("badUser"));
    }
}
