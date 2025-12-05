package com.appointmentProject.backend.service;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.Nurse;
import com.appointmentProject.backend.repository.NurseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**********************************************************************************************
 * NurseService.java
 *
 * Service layer for handling business logic and database communication related to Nurse records.
 *
 * Supports:
 *      - Insert (create)
 *      - Delete (remove)
 *      - Update (modify existing nurse data)
 *      - Select (retrieve all, by ID, or by full name)
 *
 *
 * @author Alexisp Patino
 * @since 12/03/2025
 * @version 1.0
 **********************************************************************************************/


@Service
public class NurseService {

    @Autowired
    private NurseRepository nurseRepo;

    // CREATE
    public Nurse addNurse(Nurse nurse) {
        return nurseRepo.save(nurse);
    }

    // DELETE
    public void removeNurse(int id) {
        nurseRepo.deleteById(id);
    }

    // UPDATE
    public Nurse updateNurse(Nurse update) {
        Nurse existing = nurseRepo.findById(update.getId())
                .orElseThrow(() ->
                        new RecordNotFoundException("Nurse with ID " + update.getId() + " was not found.")
                );

        existing.setFirstName(update.getFirstName());
        existing.setLastName(update.getLastName());
        existing.setPhone(update.getPhone());
        existing.setEmail(update.getEmail());
        existing.setAddress(update.getAddress());

        return nurseRepo.save(existing);
    }

    // GET ALL
    public List<Nurse> getAllNurses() {
        return nurseRepo.findAll();
    }

    // GET BY ID
    public Optional<Nurse> getNurseById(int id) {
        return nurseRepo.findById(id);
    }

    // GET BY UNIQUE NAME
    public Optional<Nurse> getByFullName(String first, String last) {
        return nurseRepo.findByFirstNameAndLastName(first, last);
    }
}
