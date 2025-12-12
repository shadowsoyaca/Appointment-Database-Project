package com.appointmentProject.backend.repository;

import com.appointmentProject.backend.model.LabOrder;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*************************************************************************************************
 *  LabOrderRepositoryTest.java
 *
 *      Integration tests to ensure LabOrderRepository queries execute correctly.
 *      Uses the REAL database connection.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 12/03/2025
 ************************************************************************************************/
@Transactional
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LabOrderRepositoryTest {

    @Autowired
    private LabOrderRepository labOrderRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void resetDatabase() {
        entityManager.createQuery("DELETE FROM LabOrder").executeUpdate();
    }

    @Test
    void testSaveAndFindById() {
        LabOrder order = new LabOrder.Builder(
                1,
                10,
                "Liver panel"
        )
                .dateOfCompletion(LocalDateTime.now())
                .results(false)
                .build();

        labOrderRepository.save(order);

        // Retrieve
        LabOrder found = labOrderRepository.findById(order.getId())
                .orElseThrow(() -> new AssertionError("LabOrder not found"));

        assertEquals("Liver panel", found.getTestingPurpose());
    }


}
