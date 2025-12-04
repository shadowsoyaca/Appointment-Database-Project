package com.appointmentProject.backend.service;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.LabOrder;
import com.appointmentProject.backend.repository.LabOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/******************************************************************************************************************
 * LabOrderService.java
 *
 *      This service class calls on the LabOrderRepository to communicate with the database.
 *
 *      Types of queries:
 *          - Insert
 *          - Delete
 *          - Update
 *          - Select (all, by id, by appointment id)
 *
 *      Later updates: validation rules and cross-table checks.
 *
 * @author Matthew
 * @version 1.0
 * @since 12/03/2025
 ******************************************************************************************************************/
@Service
public class LabOrderService {

    @Autowired
    LabOrderRepository labRepo;

    // CREATE
    public LabOrder addLabOrder(LabOrder order) {
        return labRepo.save(order);
    }

    // DELETE
    public void removeLabOrder(LabOrder order) {
        labRepo.deleteById(order.getId());
    }

    // UPDATE
    public LabOrder updateLabOrder(LabOrder update) {
        LabOrder existing = labRepo.findById(update.getId())
                .orElseThrow(() ->
                        new RecordNotFoundException("LabOrder with ID " + update.getId() + " was not found.")
                );

        existing.setAppointmentId(update.getAppointmentId());
        existing.setProviderRequesterId(update.getProviderRequesterId());
        existing.setProviderReceiverId(update.getProviderReceiverId());
        existing.setNurseId(update.getNurseId());
        existing.setPatientId(update.getPatientId());
        existing.setDateOfCompletion(update.getDateOfCompletion());
        existing.setTestingPurpose(update.getTestingPurpose());
        existing.setResults(update.getResults());

        return labRepo.save(existing);
    }

    // GET ALL
    public List<LabOrder> getAllLabOrders() {
        return labRepo.findAll();
    }

    // GET BY ID
    public Optional<LabOrder> getLabOrderById(int id) {
        return labRepo.findById(id);
    }

    // GET BY APPOINTMENT
    public List<LabOrder> getByAppointmentId(int appointmentId) {
        return labRepo.findByAppointmentId(appointmentId);
    }

    // GET BY PATIENT
    public List<LabOrder> getByPatientId(int patientId) {
        return labRepo.findByPatientId(patientId);
    }

    // GET BY PROVIDER REQUESTER
    public List<LabOrder> getByProviderRequesterId(int providerRequesterId) {
        return labRepo.findByProviderRequesterId(providerRequesterId);
    }

    // GET BY PROVIDER RECEIVER
    public List<LabOrder> getByProviderReceiverId(Integer providerReceiverId) {
        return labRepo.findByProviderReceiverId(providerReceiverId);
    }

    // GET BY NURSE ID
    public List<LabOrder> getByNurseId(Integer nurseId) {
        return labRepo.findByNurseId(nurseId);
    }

    // GET BY TESTING PURPOSE
    public List<LabOrder> getByTestingPurpose(String testingPurpose) {
        return labRepo.findByTestingPurpose(testingPurpose);
    }
}
