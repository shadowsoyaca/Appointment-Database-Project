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
                    () -> assertEquals(staff1, availability.getStaff_id()),
                    () -> assertEquals(type1, availability.getStaff_type()),
                    () -> assertEquals(day1, availability.getDay_of_week()),
                    () -> assertEquals(start1, availability.getStart_time()),
                    () -> assertEquals(end1, availability.getEnd_time())
            );

            //setter test
            availability.setStaff_id(staff2);
            availability.setStaff_type(type2);
            availability.setDay_of_week(day2);
            availability.setStart_time(start2);
            availability.setEnd_time(end2);

            assertAll(
                    () -> assertEquals(staff2, availability.getStaff_id()),
                    () -> assertEquals(type2, availability.getStaff_type()),
                    () -> assertEquals(day2, availability.getDay_of_week()),
                    () -> assertEquals(start2, availability.getStart_time()),
                    () -> assertEquals(end2, availability.getEnd_time())
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
