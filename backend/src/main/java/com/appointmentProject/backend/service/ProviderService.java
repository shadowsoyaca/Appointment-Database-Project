package com.appointmentProject.backend.service;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.Provider;
import com.appointmentProject.backend.repository.ProviderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**********************************************************************************************
 * ProviderService.java
 *
 * Service layer for handling Provider CRUD operations and validation.
 *
 * @author Alexis Patino
 * @since 12/06/2025
 * @version 1.0
 **********************************************************************************************/
@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepo;

    // CREATE
    public Provider addProvider(Provider p) {

        if (p.getFirstName() == null || p.getLastName() == null ||
            p.getPhone() == null || p.getEmail() == null ||
            p.getSpecialty() == null || p.getAddress() == null) {
            throw new IllegalArgumentException("All provider fields must be non-null.");
        }

        return providerRepo.save(p);
    }

    // GET ALL
    public List<Provider> getAllProviders() {
        return providerRepo.findAll();
    }

    // GET BY ID
    public Provider getProvider(int id) {
        return providerRepo.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Provider not found with ID: " + id));
    }

    // GET BY NAME
    public Provider getProviderByName(String first, String last) {
        return providerRepo.findByFirstNameAndLastName(first, last)
                .orElseThrow(() ->
                        new RecordNotFoundException("Provider not found: " + first + " " + last));
    }

    // UPDATE
    public Provider updateProvider(int id, Provider update) {
        Provider existing = getProvider(id);

        existing.setFirstName(update.getFirstName());
        existing.setLastName(update.getLastName());
        existing.setPhone(update.getPhone());
        existing.setEmail(update.getEmail());
        existing.setSpecialty(update.getSpecialty());
        existing.setAddress(update.getAddress());

        return providerRepo.save(existing);
    }

    // DELETE
    public void removeProvider(int id) {
        if (!providerRepo.existsById(id)) {
            throw new RecordNotFoundException("Provider not found with ID: " + id);
        }
        providerRepo.deleteById(id);
    }
}
