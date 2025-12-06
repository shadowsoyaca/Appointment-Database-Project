    /**********************************************************************************************
     * AccountRepository.java
     *
     * Repository interface for the LabOrder entity.
     *
     * @author Matthew Kiyono
     * @since 12/3/2025
     * @version 1.0
     **********************************************************************************************/
    package com.appointmentProject.backend.repository;

    import com.appointmentProject.backend.model.Account;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.Optional;

    @Repository
    public interface AccountRepository extends JpaRepository<Account, String> {

        // LOGIN
        Account findByUsernameAndPassword(String username, String password);

        // FIND BY EMAIL
        Optional<Account> findByEmail(String email);


        // FIND BY USERNAME
        Optional<Account> findByUsername(String username);


        // CREATE + UPDATE (inherited from JpaRepository)
        // - accountRepository.save(account);

        // DELETE (inherited from JpaRepository)
        // - accountRepository.delete(account);
        // - accountRepository.deleteById(username);
    }

