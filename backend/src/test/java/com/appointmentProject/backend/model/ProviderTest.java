package com.appointmentProject.backend.model;
import com.appointmentProject.backend.util.NullString;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/***************************************************************************
 *   ProviderTest.java
 *
 *          This will test the Construction, getters, setters, and
 *          toString of the Provider model.
 *          Prioritizes data integrity over logic in these tests.
 *
 * @author Matthew Kiyono
 * @version 1.1
 * @since 11/06/2025
 ****************************************************************************/
public class ProviderTest {

    @Test
    void testGettersAndSetters() {

        //Test Object
        int id1 = 55440093, id2 = 23495888;
        String first1 = "Sarah", first2 = "Kathryn";
        String last1 = "Kimono", last2 = "Brewer";
        String phone1 = "3342039921", phone2 = "7749480032";
        String email1 = "skimono@aurora.org", email2 = "kbrewer@ascension.com";
        String special1 = "Surgeon", special2 = "Family Practice";
        String address1 = "42nd St", address2 = "908 Lake Ct";

        Provider doc = new Provider(id1, first1, last1, phone1, email1, special1, address1);

        //getter tests
        assertAll(
                () -> assertEquals(id1, doc.getId()),
                () -> assertEquals(first1, doc.getFirstName()),
                () -> assertEquals(last1, doc.getLastName()),
                () -> assertEquals(phone1, doc.getPhone()),
                () -> assertEquals(email1, doc.getEmail()),
                () -> assertEquals(special1, doc.getSpecialty()),
                () -> assertEquals(address1, doc.getAddress())
        );

        //setter tests
        doc.setId(id2);
        doc.setFirstName(first2);
        doc.setLastName(last2);
        doc.setPhone(phone2);
        doc.setEmail(email2);
        doc.setSpecialty(special2);
        doc.setAddress(address2);

        assertAll(
                () -> assertEquals(id2, doc.getId()),
                () -> assertEquals(first2, doc.getFirstName()),
                () -> assertEquals(last2, doc.getLastName()),
                () -> assertEquals(phone2, doc.getPhone()),
                () -> assertEquals(email2, doc.getEmail()),
                () -> assertEquals(special2, doc.getSpecialty()),
                () -> assertEquals(address2, doc.getAddress())
        );
    }

    @Test
    void testToString() {
        Provider doc = new Provider(63462783, "Lauren", "Mayson", "2449309433",
                "lmayson@aurora.org", "Pediatrician", "123 S Sunny Ln");

        String expected =
                "Provider:" +
                        "\nProvider id: 63462783" +
                        "\nFirst name: Lauren" +
                        "\nLast name: Mayson" +
                        "\nPhone: 2449309433" +
                        "\nEmail: lmayson@aurora.org" +
                        "\nSpecialty: Pediatrician" +
                        "\nAddress: 123 S Sunny Ln" +  "\n";

        assertEquals(expected, doc.toString());
    }
}
