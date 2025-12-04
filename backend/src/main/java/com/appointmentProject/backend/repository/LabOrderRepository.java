package com.appointmentProject.backend.repository;

import com.appointmentProject.backend.model.LabOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**********************************************************************************************
 * LabOrderRepository.java
 *
 * Repository interface for the LabOrder entity.
 *
 * @author Matthew Kiyono
 * @since 12/3/2025
 * @version 1.0
 **********************************************************************************************/
@Repository
public interface LabOrderRepository extends JpaRepository<LabOrder, Integer> {

    List<LabOrder> findByAppointmentId(int appointmentId);

    List<LabOrder> findByPatientId(int patientId);

    List<LabOrder> findByProviderRequesterId(int providerRequesterId);

    List<LabOrder> findByProviderReceiverId(Integer providerReceiverId);

    List<LabOrder> findByNurseId(Integer nurseId);

    List<LabOrder> findByTestingPurpose(String testingPurpose);
}

