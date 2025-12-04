package com.appointmentProject.backend.controller;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.Insurance;
import com.appointmentProject.backend.service.InsuranceService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/***************************************************************************************
 *   InsuranceControllerUnitTest.java
 *
 *      A unit test determining if the controller test is functioning.
 * 
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 11/23/2025
 * ************************************************************************************/
public class InsuranceControllerUnitTest {

    private InsuranceController controller;
    private InsuranceService mockService;

    @BeforeEach
    void setup() {
        mockService = Mockito.mock(InsuranceService.class);
        controller = new InsuranceController();
        // manually inject service
        controller.insService = mockService;
    }

    @Test
    void testAddInsurance() {
        Insurance input = new Insurance(0, "StateFarm", "5551112222",
                "jake@statefarm.com", "123 Farm Ln");

        Insurance saved = new Insurance(1, "StateFarm", "5551112222",
                "jake@statefarm.com", "123 Farm Ln");

        when(mockService.addInsurance(any())).thenReturn(saved);

        ResponseEntity<Insurance> response = controller.addInsurance(input);

        assertEquals(1, response.getBody().getId());
        assertEquals("StateFarm", response.getBody().getInsuranceName());
    }

    @Test
    void testGetById() {
        Insurance ins = new Insurance(5, "BlueCross", "1112223333",
                "blue@cross.com", "Shield Rd");

        when(mockService.getInsuranceById(5)).thenReturn(Optional.of(ins));

        ResponseEntity<Insurance> response = controller.getById(5);

        assertEquals(5, response.getBody().getId());
        assertEquals("BlueCross", response.getBody().getInsuranceName());
    }

    @Test
    void testGetAll() {
        List<Insurance> list = List.of(
                new Insurance(1, "Aetna", "123", "a@etna.com", "Aetna Ave"),
                new Insurance(2, "StateFarm", "456", "sf@state.com", "Farm Lane")
        );

        when(mockService.getAllInsurance()).thenReturn(list);

        ResponseEntity<List<Insurance>> response = controller.getAll();

        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetByInsuranceName() {
        List<Insurance> list = List.of(
                new Insurance(10, "StateFarm", "9999", "s@f.com", "Farm Rd")
        );

        when(mockService.getByInsuranceName("StateFarm")).thenReturn(list);

        ResponseEntity<List<Insurance>> response = controller.getByInsuranceName("StateFarm");

        assertEquals(1, response.getBody().size());
        assertEquals("StateFarm", response.getBody().get(0).getInsuranceName());
    }

    @Test    void testUpdateInsurance() {
        Insurance original = new Insurance(3, "OldName", "555", "old@mail.com", "Old St");
        Insurance updated = new Insurance(3, "NewName", "555", "new@mail.com", "New St");

        when(mockService.updateInsurance(any())).thenReturn(updated);

        ResponseEntity<Insurance> response = controller.updateInsurance(original);

        assertEquals("NewName", response.getBody().getInsuranceName());
    }

    @Test
    void testDeleteInsurance() {
        Insurance existing = new Insurance(3, "Test", "123", "t@test.com", "Road");

        when(mockService.getInsuranceById(3)).thenReturn(Optional.of(existing));

        ResponseEntity<String> response = controller.deleteInsurance(3);

        assertEquals("Insurance record removed successfully.", response.getBody());
    }

    @Test
    void testGetById_NotFound() {
        when(mockService.getInsuranceById(100)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class,
                () -> controller.getById(100));
    }
}
