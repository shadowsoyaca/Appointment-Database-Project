package com.appointmentProject.backend.controller;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.LabOrder;
import com.appointmentProject.backend.service.LabOrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/***********************************************************************************************
 *   LabOrderControllerUnitTest.java
 *
 *      TRUE unit tests for LabOrderController — LabOrderService is mocked.
 *
 * @author Matthew
 * @version 1.0
 * @since 12/03/2025
 ***********************************************************************************************/
public class LabOrderControllerUnitTest {

    private LabOrderController controller;
    private LabOrderService mockService;

    private LabOrder sampleOrder;

    @BeforeEach
    void setup() {
        mockService = Mockito.mock(LabOrderService.class);
        controller = new LabOrderController();
        // manually inject service
        controller.labService = mockService;

        sampleOrder = new LabOrder.Builder(
                0,
                10,
                5,
                88,
                "Blood test"
        )
                .nurseId(20)
                .dateOfCompletion(LocalDateTime.now())
                .results(false)
                .build();
    }

    @Test
    void testAddLabOrder() {
        when(mockService.addLabOrder(any(LabOrder.class))).thenReturn(sampleOrder);

        ResponseEntity<LabOrder> response = controller.addLabOrder(sampleOrder);

        assertEquals("Blood test", response.getBody().getTestingPurpose());
        verify(mockService, times(1)).addLabOrder(any(LabOrder.class));
    }

    @Test
    void testGetAllLabOrders() {
        when(mockService.getAllLabOrders()).thenReturn(List.of(sampleOrder));

        ResponseEntity<List<LabOrder>> response = controller.getAll();

        assertEquals(1, response.getBody().size());
        verify(mockService, times(1)).getAllLabOrders();
    }

    @Test
    void testGetById_Found() {
        when(mockService.getLabOrderById(1)).thenReturn(Optional.of(sampleOrder));

        ResponseEntity<LabOrder> response = controller.getById(1);

        assertEquals(10, response.getBody().getAppointmentId());
        verify(mockService, times(1)).getLabOrderById(1);
    }

    @Test
    void testGetById_NotFound() {
        when(mockService.getLabOrderById(99)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class,
                () -> controller.getById(99));
    }

    @Test
    void testGetByAppointmentId() {
        when(mockService.getByAppointmentId(10)).thenReturn(List.of(sampleOrder));

        ResponseEntity<List<LabOrder>> response = controller.getByAppointmentId(10);

        assertEquals(1, response.getBody().size());
        verify(mockService, times(1)).getByAppointmentId(10);
    }

    @Test
    void testUpdateLabOrder() {
        when(mockService.updateLabOrder(any(LabOrder.class))).thenReturn(sampleOrder);

        ResponseEntity<LabOrder> response = controller.updateLabOrder(sampleOrder);

        assertEquals("Blood test", response.getBody().getTestingPurpose());
        verify(mockService, times(1)).updateLabOrder(any(LabOrder.class));
    }

    @Test
    void testDeleteLabOrder() {
        when(mockService.getLabOrderById(1)).thenReturn(Optional.of(sampleOrder));
        doNothing().when(mockService).removeLabOrder(sampleOrder);

        ResponseEntity<String> response = controller.deleteLabOrder(1);

        assertEquals("LabOrder record removed successfully.", response.getBody());
        verify(mockService, times(1)).getLabOrderById(1);
        verify(mockService, times(1)).removeLabOrder(sampleOrder);
    }

@Test
    void testGetByPatient() {
        when(mockService.getByPatientId(88)).thenReturn(List.of(sampleOrder));

        ResponseEntity<List<LabOrder>> response = controller.getByPatient(88);

        assertEquals(1, response.getBody().size());
        verify(mockService, times(1)).getByPatientId(88);
    }

    @Test
    void testGetByProviderRequester() {
        when(mockService.getByProviderRequesterId(5)).thenReturn(List.of(sampleOrder));

        ResponseEntity<List<LabOrder>> response = controller.getByProviderRequester(5);

        assertEquals(1, response.getBody().size());
        verify(mockService, times(1)).getByProviderRequesterId(5);
    }

    @Test
    void testGetByProviderReceiver() {
        when(mockService.getByProviderReceiverId(20)).thenReturn(List.of(sampleOrder));

        ResponseEntity<List<LabOrder>> response = controller.getByProviderReceiver(20);

        assertEquals(1, response.getBody().size());
        verify(mockService, times(1)).getByProviderReceiverId(20);
    }

    @Test
    void testGetByNurse() {
        when(mockService.getByNurseId(33)).thenReturn(List.of(sampleOrder));

        ResponseEntity<List<LabOrder>> response = controller.getByNurse(33);

        assertEquals(1, response.getBody().size());
        verify(mockService, times(1)).getByNurseId(33);
    }

    @Test
    void testGetByTestingPurpose() {
        when(mockService.getByTestingPurpose("Blood test")).thenReturn(List.of(sampleOrder));

        ResponseEntity<List<LabOrder>> response = controller.getByPurpose("Blood test");

        assertEquals(1, response.getBody().size());
        verify(mockService, times(1)).getByTestingPurpose("Blood test");
    }

}