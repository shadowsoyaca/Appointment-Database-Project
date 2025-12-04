
package com.appointmentProject.backend.service;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.LabOrder;
import com.appointmentProject.backend.repository.LabOrderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/***************************************************************************************************
 * LabOrderServiceTest.java
 *
 *      Tests the LabOrderService using Mockito to mock the repository.
 *      NO actual database calls are made.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 12/03/2025
 ***************************************************************************************************/
@ExtendWith(MockitoExtension.class)
public class LabOrderServiceTest {

    @Mock
    private LabOrderRepository labRepo;

    @InjectMocks
    private LabOrderService labService;

    private LabOrder sampleOrder;

    @BeforeEach
    void init() {
        sampleOrder = new LabOrder.Builder(
                1,
                10,
                5,
                88,
                "X-ray"
        )
                .nurseId(33)
                .dateOfCompletion(LocalDateTime.now())
                .results(false)
                .build();
    }

    @Test
    void testAddLabOrder() {
        when(labRepo.save(sampleOrder)).thenReturn(sampleOrder);

        LabOrder result = labService.addLabOrder(sampleOrder);

        assertNotNull(result);
        assertEquals("X-ray", result.getTestingPurpose());
        verify(labRepo, times(1)).save(sampleOrder);
    }

    @Test
    void testUpdateLabOrder_Success() {
        LabOrder updated = new LabOrder.Builder(
                1,
                10,
                5,
                88,
                "Updated Reason"
        )
                .results(true)
                .build();

        when(labRepo.findById(1)).thenReturn(Optional.of(sampleOrder));
        when(labRepo.save(any(LabOrder.class))).thenReturn(updated);

        LabOrder result = labService.updateLabOrder(updated);

        assertEquals("Updated Reason", result.getTestingPurpose());
        assertTrue(result.getResults());
        verify(labRepo, times(1)).findById(1);
        verify(labRepo, times(1)).save(any(LabOrder.class));
    }

    @Test
    void testUpdateLabOrder_NotFound() {
        LabOrder updated = new LabOrder.Builder(
                99,
                10,
                5,
                88,
                "Doesn't matter"
        ).build();

        when(labRepo.findById(99)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class,
                () -> labService.updateLabOrder(updated));
    }

    @Test
    void testGetAllLabOrders() {
        when(labRepo.findAll()).thenReturn(List.of(sampleOrder));

        List<LabOrder> list = labService.getAllLabOrders();

        assertEquals(1, list.size());
        verify(labRepo, times(1)).findAll();
    }

    @Test
    void testGetLabOrderById() {
        when(labRepo.findById(1)).thenReturn(Optional.of(sampleOrder));

        Optional<LabOrder> result = labService.getLabOrderById(1);

        assertTrue(result.isPresent());
        assertEquals(10, result.get().getAppointmentId());
        verify(labRepo, times(1)).findById(1);
    }

    @Test
    void testDeleteLabOrder() {
        when(labRepo.findById(1)).thenReturn(Optional.of(sampleOrder));

        labService.removeLabOrder(sampleOrder);

        verify(labRepo, times(1)).deleteById(sampleOrder.getId());
    }

    @Test
    void testGetByAppointmentId() {
        when(labRepo.findByAppointmentId(10)).thenReturn(List.of(sampleOrder));

        List<LabOrder> results = labService.getByAppointmentId(10);

        assertEquals(1, results.size());
        verify(labRepo, times(1)).findByAppointmentId(10);
    }


    @Test
    void testGetByPatientId() {
        when(labRepo.findByPatientId(88)).thenReturn(List.of(sampleOrder));

        List<LabOrder> results = labService.getByPatientId(88);

        assertEquals(1, results.size());
        verify(labRepo, times(1)).findByPatientId(88);
    }

    @Test
    void testGetByProviderRequesterId() {
        when(labRepo.findByProviderRequesterId(5)).thenReturn(List.of(sampleOrder));

        List<LabOrder> results = labService.getByProviderRequesterId(5);

        assertEquals(1, results.size());
        verify(labRepo, times(1)).findByProviderRequesterId(5);
    }

    @Test
    void testGetByProviderReceiverId() {
        when(labRepo.findByProviderReceiverId(20)).thenReturn(List.of(sampleOrder));

        List<LabOrder> results = labService.getByProviderReceiverId(20);

        assertEquals(1, results.size());
        verify(labRepo, times(1)).findByProviderReceiverId(20);
    }

    @Test
    void testGetByNurseId() {
        when(labRepo.findByNurseId(33)).thenReturn(List.of(sampleOrder));

        List<LabOrder> results = labService.getByNurseId(33);

        assertEquals(1, results.size());
        verify(labRepo, times(1)).findByNurseId(33);
    }

    @Test
    void testGetByTestingPurpose() {
        when(labRepo.findByTestingPurpose("X-ray")).thenReturn(List.of(sampleOrder));

        List<LabOrder> results = labService.getByTestingPurpose("X-ray");

        assertEquals(1, results.size());
        verify(labRepo, times(1)).findByTestingPurpose("X-ray");
    }
}