package com.appointmentProject.backend.model;
import com.appointmentProject.backend.util.NullString;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/***************************************************************************
 *   NurseTest.java
 *
 *          This will test the Construction, getters, setters, and
 *          toString of the Nurse model.
 *          Prioritizes data integrity over logic in these tests.
 *
 * @author Matthew Kiyono
 * @version 1.1
 * @since 11/05/2025
 ****************************************************************************/
public class NurseTest {

    @Test
    void testGetterAndSetter() {

        //Test Object
        int id1 = 45539449, id2 = 45535349;
        String  first1 = "Jerry", first2 = "Connor";
        String last1 = "Goldman", last2 = "Bellsworth";
        String phone1 = "3335998049", phone2 = "29287766468";
        String email1 = "jgoldman@uwm.edu", email2 = "cbellsworth@uwm.edu";
        String address1 = "43000 Divinity Ln", address2 = "10020 Worshfire St";

        Nurse nurse = new Nurse(id1, first1, last1, phone1, email1, address1);

        //getter tests
        assertAll(
                () -> assertEquals(id1, nurse.getId()),
                () -> assertEquals(first1, nurse.getFirstName()),
                () -> assertEquals(last1, nurse.getLastName()),
                () -> assertEquals(phone1, nurse.getPhone()),
                () -> assertEquals(email1, nurse.getEmail()),
                () -> assertEquals(address1, nurse.getAddress())
        );

        //setter tests
        nurse.setId(id2);
        nurse.setFirstName(first2);
        nurse.setLastName(last2);
        nurse.setPhone(phone2);
        nurse.setEmail(email2);
        nurse.setAddress(address2);

        assertAll(
                () -> assertEquals(id2, nurse.getId()),
                () -> assertEquals(first2, nurse.getFirstName()),
                () -> assertEquals(last2, nurse.getLastName()),
                () -> assertEquals(phone2, nurse.getPhone()),
                () -> assertEquals(email2, nurse.getEmail()),
                () -> assertEquals(address2, nurse.getAddress())
        );
    }

    @Test
    void testToString() {
        Nurse nurse = new Nurse(1025, "Paige", "Joy",
                "5553296664", "nurseJoy@pokemonCenter.com", "33 Pallet Town");

        String expectation =
                "Nurse:\n" +
                        "Nurse id: 1025" +
                        "\nFirst name: Paige" +
                        "\nLast name: Joy" +
                        "\nPhone: 5553296664" +
                        "\nEmail: nurseJoy@pokemonCenter.com" +
                        "\nAddress: 33 Pallet Town" +  "\n";

        assertEquals(expectation, nurse.toString());
    }
}
