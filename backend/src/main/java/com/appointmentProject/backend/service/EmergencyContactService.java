package com.appointmentProject.backend.service;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.EmergencyContact;
import com.appointmentProject.backend.repository.EmergencyContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**********************************************************************************************
 * EmergencyContactService.java
 *
 *      This service class communicates with the EmergencyContactRepository
 *      to perform CRUD operations on Emergency Contact records.
 *
 *      Supports:
 *          - Insert
 *          - Delete
 *          - Update
 *          - Select (all, by id, by full name)
 *
 * @author Alexis Patino
 * @version 1.0
 * @since 12/05/2025
 **********************************************************************************************/
@Service
public class EmergencyContactService {

    @Autowired
    private EmergencyContactRepository ecRepo;

    // CREATE
    public EmergencyContact addEmergencyContact(EmergencyContact contact) {
        return ecRepo.save(contact);
    }

    // DELETE
    public void removeEmergencyContact(int id) {
        ecRepo.deleteById(id);
    }

    // UPDATE
    public EmergencyContact updateEmergencyContact(EmergencyContact update) {
        EmergencyContact existing = ecRepo.findById(update.getId())
                .orElseThrow(() ->
                        new RecordNotFoundException("EmergencyContact with ID "
                                + update.getId() + " was not found.")
                );

        existing.setFirstName(update.getFirstName());
        existing.setLastName(update.getLastName());
        existing.setPhone(update.getPhone());
        existing.setEmail(update.getEmail());
        existing.setAddress(update.getAddress());

        return ecRepo.save(existing);
    }

    // GET ALL
    public List<EmergencyContact> getAllEmergencyContacts() {
        return ecRepo.findAll();
    }

    // GET BY ID
    public Optional<EmergencyContact> getEmergencyContactById(int id) {
        return ecRepo.findById(id);
    }

    // GET BY UNIQUE NAME COMBINATION
    public Optional<EmergencyContact> getByFullName(String first, String last) {
        return ecRepo.findByFirstNameAndLastName(first, last);
    }
}
