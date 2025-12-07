package com.appointmentProject.backend.repository;

import com.appointmentProject.backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**********************************************************************************************
 * RoomRepository.java
 *
 * Repository interface for the Room entity.
 *
 * @author Alexis Patino
 * @since 12/05/2025
 * @version 1.0
 **********************************************************************************************/
@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    Room findByRoomNumber(String roomNumber);

    boolean existsByRoomNumber(String roomNumber);
}

