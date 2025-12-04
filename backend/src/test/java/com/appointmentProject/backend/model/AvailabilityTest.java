package com.appointmentProject.backend.model;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/***************************************************************************
 *   AvailabilityTest.java
 *
 *          This will test the Construction, getters, setters, and
 *          toString of the Availability model.
 *          Prioritizes data integrity over logic in these tests.
 *
 * @author Matthew Kiyono
 * @version 1.1
 * @since 11/05/2025
 ****************************************************************************/

public class AvailabilityTest {

        //Test of getters and setters
        @Test
        void testGetterAndSetter(){

            //Test Model
                //Required
            int staff1 = 12309874, staff2 = 43507611;
            String type1 = "Provider", type2 = "Nurse";
            String day1 = "Mon", day2 = "Tue";
            LocalTime start1 = LocalTime.of(5, 0), start2 = LocalTime.of(17,20);
            LocalTime end1 = LocalTime.of(14, 0), end2 = LocalTime.of(5, 30);

            Availability availability = new Availability(staff1, type1, day1, start1, end1);

            //getter test
            assertAll(
                    () -> assertEquals(staff1, availability.getStaffId()),
                    () -> assertEquals(type1, availability.getStaffType()),
                    () -> assertEquals(day1, availability.getDayOfWeek()),
                    () -> assertEquals(start1, availability.getStartTime()),
                    () -> assertEquals(end1, availability.getEndTime())
            );

            //setter test
            availability.setStaffId(staff2);
            availability.setStaffType(type2);
            availability.setDayOfWeek(day2);
            availability.setStartTime(start2);
            availability.setEndTime(end2);

            assertAll(
                    () -> assertEquals(staff2, availability.getStaffId()),
                    () -> assertEquals(type2, availability.getStaffType()),
                    () -> assertEquals(day2, availability.getDayOfWeek()),
                    () -> assertEquals(start2, availability.getStartTime()),
                    () -> assertEquals(end2, availability.getEndTime())
            );
        }

        @Test
        void testToString(){
            Availability availability = new Availability(3, "Provider",
                    "Thu", LocalTime.of(8,45), LocalTime.of(16, 45));


            String expected =
                    "Availability:\n" +
                            "\nAV id: 3" +
                            "\nstaff_type: Provider" +
                            "\nday_of_week: Thu" +
                            "\nstart_time: 08:45" +
                            "\nend_time: 16:45" + "\n";

            assertEquals(expected, availability.toString());
        }
}
