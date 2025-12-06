package com.appointmentProject.backend.service;

import com.appointmentProject.backend.model.Pharmacy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/******************************************************************************************
 * PharmacyService.java
 *
 *      Service layer for handling Pharmacy data.
 *
 * @author Aisha Ali
 * @since 12/06/2025
 ******************************************************************************************/
@Service
public class PharmacyService {

    private final List<Pharmacy> pharmacies = new ArrayList<>();

    public Pharmacy addPharmacy(Pharmacy pharmacy) {
        pharmacies.add(pharmacy);
        return pharmacy;
    }

    public List<Pharmacy> getAllPharmacies() {
        return pharmacies;
    }

    public Optional<Pharmacy> getPharmacyById(int id) {
        return pharmacies.stream().filter(p -> p.getId() == id).findFirst();
    }

    public List<Pharmacy> getByName(String name) {
        List<Pharmacy> result = new ArrayList<>();
        for (Pharmacy p : pharmacies) {
            if (p.getName().equalsIgnoreCase(name)) {
                result.add(p);
            }
        }
        return result;
    }

    public Pharmacy updatePharmacy(Pharmacy pharmacy) {
        pharmacies.removeIf(p -> p.getId() == pharmacy.getId());
        pharmacies.add(pharmacy);
        return pharmacy;
    }

    public void removePharmacy(Pharmacy pharmacy) {
        pharmacies.remove(pharmacy);
    }
}
