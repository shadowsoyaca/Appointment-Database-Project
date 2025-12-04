package com.appointmentProject.backend.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/***************************************************************************
 *   RoomTest.java
 *
 *          This will test the Construction, getters, setters, and
 *          toString of the Room model.
 *          Prioritizes data integrity over logic in these tests.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 11/06/2025
 ****************************************************************************/
public class RoomTest {

    @Test
    void testGetterAndSetters(){
        //Test Object
        String number1 = "123", number2 = "456";
        int floor1 = 1, floor2 = 4;

        Room room = new Room(number1, floor1);

        //getter test
        assertAll(
                () -> assertEquals(number1, room.getRoomNumber()),
                () -> assertEquals(floor1, room.getFloorNumber())
        );

        //setter test
        room.setRoomNumber(number2);
        room.setFloorNumber(floor2);

        assertAll(
                () -> assertEquals(number2, room.getRoomNumber()),
                () -> assertEquals(floor2, room.getFloorNumber())
        );
    }

    @Test
    void testToString(){
        //Test Object
        Room room = new Room("577", 5);

        String expected =
                "Room:" +
                "\nNumber: 577" +
                "\nFloor: 5" + "\n";

        assertEquals(expected, room.toString());
    }

}
