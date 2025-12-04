
package com.appointmentProject.backend.service;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.Insurance;
import com.appointmentProject.backend.repository.InsuranceRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

/***************************************************************************************************
 * InsuranceServiceTest.java
 *
 *      This file tests the InsuranceService class, ensuring that the correct repository methods
 *      are called, exceptions are thrown when appropriate, and the service returns the expected
 *      data. Mockito is used to mock the InsuranceRepository so that NO actual database calls
 *      are made. These are TRUE unit tests of the service layer.
 *
 *      What is tested:
 *      1. Insert Method (addInsurance)
 *      2. Delete Method (removeInsurance)
 *      3. Update Method (updateInsurance) — including success & failure cases
 *      4. Select All
 *      5. Select By ID
 *      6. Select By Insurance Name (custom repo method)
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 11/20/2025
 ***************************************************************************************************/
@ExtendWith(MockitoExtension.class)
public class InsuranceServiceTest {

    // Mock the repository (fake version of the database layer)
    @Mock
    private InsuranceRepository insRepo;

    // Inject the mock into the service under test
    @InjectMocks
    private InsuranceService insService;

    private Insurance sampleA;
    private Insurance sampleB;

    @BeforeEach
    void setup() {
        sampleA = new Insurance(1, "StateFarm", "3322030045",
                "jake@statefarm.org", "123 Farmhand Ave");

        sampleB = new Insurance(2, "Blue Cross", "8771234567",
                "blue@cross.org", "3399 Shield Ln");
    }

    // ----------------------------------------------------------------------------------------------------
    // 1. INSERT TEST
    // ----------------------------------------------------------------------------------------------------
    @Test
    void testAddInsurance() {
        when(insRepo.save(sampleA)).thenReturn(sampleA);

        Insurance result = insService.addInsurance(sampleA);

        assertNotNull(result);
        assertEquals("StateFarm", result.getInsuranceName());
        verify(insRepo, times(1)).save(sampleA);
    }

    // ----------------------------------------------------------------------------------------------------
    // 2. DELETE TEST
    // ----------------------------------------------------------------------------------------------------
    @Test
    void testRemoveInsurance() {
        doNothing().when(insRepo).deleteById(sampleA.getId());

        insService.removeInsurance(sampleA);

        verify(insRepo, times(1)).deleteById(1);
    }

    // ----------------------------------------------------------------------------------------------------
    // 3a. UPDATE TEST — SUCCESS
    // ----------------------------------------------------------------------------------------------------
    @Test
    void testUpdateInsuranceSuccess() {
        when(insRepo.findById(sampleA.getId())).thenReturn(Optional.of(sampleA));
        when(insRepo.save(sampleA)).thenReturn(sampleA);

        Insurance result = insService.updateInsurance(sampleA);

        assertNotNull(result);
        verify(insRepo, times(1)).findById(1);
        verify(insRepo, times(1)).save(sampleA);
    }

    // ----------------------------------------------------------------------------------------------------
    // 3b. UPDATE TEST — FAILURE (Record Not Found)
    // ----------------------------------------------------------------------------------------------------
    @Test
    void testUpdateInsuranceThrowsException() {
        when(insRepo.findById(sampleA.getId())).thenReturn(Optional.empty());

        RecordNotFoundException ex = assertThrows(
                RecordNotFoundException.class,
                () -> insService.updateInsurance(sampleA)
        );

        assertTrue(ex.getMessage().contains("does not exist"));
        verify(insRepo, times(1)).findById(1);
        verify(insRepo, never()).save(any());
    }

    // ----------------------------------------------------------------------------------------------------
    // 4. SELECT ALL
    // ----------------------------------------------------------------------------------------------------
    @Test
    void testGetAllInsurance() {
        when(insRepo.findAll()).thenReturn(Arrays.asList(sampleA, sampleB));

        List<Insurance> list = insService.getAllInsurance();

        assertEquals(2, list.size());
        verify(insRepo, times(1)).findAll();
    }

    // ----------------------------------------------------------------------------------------------------
    // 5. SELECT BY ID
    // ----------------------------------------------------------------------------------------------------
    @Test
    void testGetInsuranceById() {
        when(insRepo.findById(1)).thenReturn(Optional.of(sampleA));

        Optional<Insurance> result = insService.getInsuranceById(1);

        assertTrue(result.isPresent());
        assertEquals("StateFarm", result.get().getInsuranceName());
        verify(insRepo, times(1)).findById(1);
    }

    // ----------------------------------------------------------------------------------------------------
    // 6. SELECT BY INSURANCE NAME
    // ----------------------------------------------------------------------------------------------------
    @Test
    void testGetInsuranceByName() {
        when(insRepo.findByInsuranceName("StateFarm")).thenReturn(List.of(sampleA));

        List<Insurance> results = insService.getByInsuranceName("StateFarm");

        assertEquals(1, results.size());
        assertEquals("StateFarm", results.get(0).getInsuranceName());
        verify(insRepo, times(1)).findByInsuranceName("StateFarm");
    }
}
