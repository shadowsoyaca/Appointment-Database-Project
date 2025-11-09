package com.appointmentProject.backend.model;
import com.appointmentProject.backend.util.NullString;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
/***************************************************************************
 *   PatientTest.java
 *
 *          This will test the Construction, getters, setters, and
 *          toString of the Patient model.
 *          Prioritizes data integrity over logic in these tests.
 *
 * @author Matthew Kiyono
 * @version 1.1
 * @since 11/05/2025
 ****************************************************************************/
public class PatientTest {

    @Test
    void testGetterAndSetter() {

        //Test Model
        int id1 = 88773344, id2 = 42327887;
        String first1 = "Anthony", first2 = "Bobby";
        String last1 = "Fiveuss", last2 = "Preyeski";
        LocalDate dob1 = LocalDate.of(2000, 4, 13),
                dob2 = LocalDate.of(1991, 8, 14);
        int age1 = 25, age2 = 34;
        double weight1 = 164.23, weight2 = 125.72;
        double height1 = 5.6, height2 = 6.4;
        String phone1 = "4140992839", phone2 = "4142445983";
        String gender1 = "Male", gender2 = null;
        String email1 = "afive@uwm.edu", email2 = null;
        Integer IN_id1 = 330022,  IN_id2 = null;
        Integer EC_id1 = 490222, EC_id2 = null;

        Patient patient = new Patient.Builder(
                id1, first1, last1, dob1, age1, weight1, height1, phone1).gender(gender1)
                .email(email1).insurance_id(IN_id1).emergencyContact_id(EC_id1).build();

        //getter test
        assertAll(
                () -> assertEquals(id1, patient.getId()),
                () -> assertEquals(first1, patient.getFirst_name()),
                () -> assertEquals(last1, patient.getLast_name()),
                () -> assertEquals(dob1, patient.getDoB()),
                () -> assertEquals(age1, patient.getAge()),
                () -> assertEquals(weight1, patient.getWeight()),
                () -> assertEquals(height1, patient.getHeight()),
                () -> assertEquals(phone1, patient.getPhone()),
                () -> assertEquals(gender1, patient.getGender()),
                () -> assertEquals(email1, patient.getEmail()),
                () -> assertEquals(IN_id1, patient.getInsurance_id()),
                () -> assertEquals(EC_id1, patient.getEmergencyContact_id())
        );

        //setter test
        patient.setId(id2);
        patient.setFirst_name(first2);
        patient.setLast_name(last2);
        patient.setDoB(dob2);
        patient.setAge(age2);
        patient.setWeight(weight2);
        patient.setHeight(height2);
        patient.setPhone(phone2);
        patient.setGender(gender2);
        patient.setEmail(email2);
        patient.setInsurance_id(IN_id2);
        patient.setEmergencyContact_id(EC_id2);

        assertAll(
                () -> assertEquals(id2, patient.getId()),
                () -> assertEquals(first2, patient.getFirst_name()),
                () -> assertEquals(last2, patient.getLast_name()),
                () -> assertEquals(dob2, patient.getDoB()),
                () -> assertEquals(age2, patient.getAge()),
                () -> assertEquals(weight2, patient.getWeight()),
                () -> assertEquals(height2, patient.getHeight()),
                () -> assertEquals(phone2, patient.getPhone()),
                () -> assertNull(patient.getGender()),
                () -> assertNull(patient.getEmail()),
                () -> assertNull(patient.getInsurance_id()),
                () -> assertNull(patient.getEmergencyContact_id())
        );
    }


    @Test
    void testToString() {

        //Test Object

        Patient patient = new Patient.Builder(
                23948805, "Leon", "Kennedy", LocalDate.of(1988,5, 22),
                38, 173.48, 5.8, "3210034897").gender("Male")
                .email("lkennedy@R4evil.com").insurance_id(31908744)
                .emergencyContact_id(69229700).build();

        String expectedA =
                "Patient:" +
                        "\nPatient ID: 23948805" +
                        "\nFirst name: Leon" +
                        "\nLast name: Kennedy" +
                        "\nPhone: 3210034897" +
                        "\nEmail: lkennedy@R4evil.com" +
                        "\nDoB: 1988-05-22" +
                        "\nAge: 38" +
                        "\nWeight: 173.48" +
                        "\nHeight: 5.8" +
                        "\nGender: Male" +
                        "\nInsurance Id: 31908744" +
                        "\nEmergency Contact ID: 69229700" + "\n";

        assertEquals(expectedA, patient.toString());

        //NullString Involvement Test
        patient.setGender(null);
        patient.setEmail(null);
        patient.setInsurance_id(null);
        patient.setEmergencyContact_id(null);

        String expectedB =
                "Patient:" +
                        "\nPatient ID: 23948805" +
                        "\nFirst name: Leon" +
                        "\nLast name: Kennedy" +
                        "\nPhone: 3210034897" +
                        "\nEmail: N/A" +
                        "\nDoB: 1988-05-22" +
                        "\nAge: 38" +
                        "\nWeight: 173.48" +
                        "\nHeight: 5.8" +
                        "\nGender: N/A" +
                        "\nInsurance Id: N/A" +
                        "\nEmergency Contact ID: N/A" + "\n";

        assertEquals(expectedB, patient.toString());

    }
}


