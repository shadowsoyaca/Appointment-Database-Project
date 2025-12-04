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
                5,
                88,
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
        assertEquals(10, found.getAppointmentId());
    }

    @Test
    void testFindByAppointmentId() {
        LabOrder order1 = new LabOrder.Builder(1, 20, 5, 88, "Test A").build();
        LabOrder order2 = new LabOrder.Builder(2, 20, 7, 90, "Test B").build();
        LabOrder order3 = new LabOrder.Builder(3, 30, 7, 90, "Test C").build();

        labOrderRepository.save(order1);
        labOrderRepository.save(order2);
        labOrderRepository.save(order3);

        List<LabOrder> results = labOrderRepository.findByAppointmentId(20);

        assertNotNull(results, "Results should not be null");
        assertEquals(2, results.size(), "There should be exactly 2 LabOrders for appointmentId 20");
        for (LabOrder lo : results) {
            assertEquals(20, lo.getAppointmentId(), "Appointment ID should be 20");
        }
    }
}
