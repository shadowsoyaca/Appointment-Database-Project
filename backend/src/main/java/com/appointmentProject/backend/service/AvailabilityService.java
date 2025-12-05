package com.appointmentProject.backend.service;

import com.appointmentProject.backend.exception.RecordNotFoundException;
import com.appointmentProject.backend.model.Availability;
import com.appointmentProject.backend.repository.AvailabilityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

/**********************************************************************************************
 * AvailabilityService.java
 *
 *      Service layer that handles business logic and database communication for
 *      Availability records.
 *
 *      Supports:
 *          - Insert (addAvailability)
 *          - Delete (removeAvailability)
 *          - Update (updateAvailability)
 *          - Select (find all, by staff, by staffType, by dayOfWeek)
 *
 * @author Alexis Patino
 * @version 1.0
 * @since 12/05/2025
 **********************************************************************************************/
@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepo;


    // CREATE
    public Availability addAvailability(Availability av) {
        validateTimes(av.getStartTime(), av.getEndTime());
        return availabilityRepo.save(av);
    }


    // DELETE
    public void removeAvailability(int id) {
        availabilityRepo.deleteById(id);
    }


    // UPDATE
    public Availability updateAvailability(Availability update) {
        Availability existing = availabilityRepo.findById(update.getStaffId())
                .orElseThrow(() ->
                        new RecordNotFoundException(
                                "Availability record not found for staff ID: " + update.getStaffId()
                        )
                );

        validateTimes(update.getStartTime(), update.getEndTime());

        existing.setStaffType(update.getStaffType());
        existing.setDayOfWeek(update.getDayOfWeek());
        existing.setStartTime(update.getStartTime());
        existing.setEndTime(update.getEndTime());

        return availabilityRepo.save(existing);
    }


    // GET ALL
    public List<Availability> getAllAvailability() {
        return availabilityRepo.findAll();
    }


    // GET BY STAFF ID
    public List<Availability> getByStaffId(int staffId) {
        return availabilityRepo.findByStaffId(staffId);
    }


    // GET BY STAFF TYPE (Provider or Nurse)
    public List<Availability> getByStaffType(String staffType) {
        return availabilityRepo.findByStaffType(staffType);
    }


    // GET BY DAY OF WEEK
    public List<Availability> getByDayOfWeek(String day) {
        return availabilityRepo.findByDayOfWeek(day);
    }


    // INTERNAL VALIDATION
    private void validateTimes(LocalTime start, LocalTime end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start time cannot be later than end time.");
        }
        if (start.isBefore(LocalTime.MIDNIGHT)) {
            throw new IllegalArgumentException("Start time cannot be earlier than 00:00.");
        }
        if (end.isAfter(LocalTime.of(23, 59))) {
            throw new IllegalArgumentException("End time cannot be later than 23:59.");
        }
    }
}
