package com.appointmentProject.backend.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalTime;

/***************************************************************************
 *   PharmacyTest.java
 *
 *          This will test the Construction, getters, setters, and
 *          toString of the Pharmacy model.
 *          Prioritizes data integrity over logic in these tests.
 *
 * @author Matthew Kiyono
 * @version 1.1
 * @since 11/06/2025
 ****************************************************************************/
public class PharmacyTest {

    @Test
    void testGettersAndSetters() {

        //Test Object
        int id1 = 73849502, id2 = 73849900;
        String name1 = "CVS", name2 = "Wallgreens";
        String phone1 = "3445678399", phone2 = "5746379950";
        String email1 = "pharmacy@cvs.org", email2 = "wallmeds@wallgreens.net";
        String address1 = "555 West Ave", address2 = "1259 Civil Rd";
        LocalTime open1 = LocalTime.of(10, 0), open2 = LocalTime.of(11, 30);
        LocalTime close1 = LocalTime.of(20, 0), close2 = LocalTime.of(21, 0);

        Pharmacy pharmacy = new Pharmacy(id1, name1, phone1, email1, address1, open1, close1);

        //Getter Test
        assertAll(
                () -> assertEquals(id1, pharmacy.getId()),
                () -> assertEquals(name1, pharmacy.getName()),
                () -> assertEquals(phone1, pharmacy.getPhone()),
                () -> assertEquals(email1, pharmacy.getEmail()),
                () -> assertEquals(address1, pharmacy.getAddress()),
                () -> assertEquals(open1, pharmacy.getStartTime()),
                () -> assertEquals(close1, pharmacy.getEndTime())
        );

        //Setter Test
        pharmacy.setId(id2);
        pharmacy.setName(name2);
        pharmacy.setPhone(phone2);
        pharmacy.setEmail(email2);
        pharmacy.setAddress(address2);
        pharmacy.setStartTime(open2);
        pharmacy.setEndTime(close2);

        assertAll(
                () -> assertEquals(id2, pharmacy.getId()),
                () -> assertEquals(name2, pharmacy.getName()),
                () -> assertEquals(phone2, pharmacy.getPhone()),
                () -> assertEquals(email2, pharmacy.getEmail()),
                () -> assertEquals(address2, pharmacy.getAddress()),
                () -> assertEquals(open2, pharmacy.getStartTime()),
                () -> assertEquals(close2, pharmacy.getEndTime())
        );
    }

    @Test
    void testToString() {
        //Test Object
        Pharmacy pharmacy = new Pharmacy(9338940, "Pokemon Center", "3294485748",
                "frontnurse@pkcenter.net", "234 Pewter Rd", LocalTime.of(3,0),
                LocalTime.of(23,0));

        String expectation =
                "Pharmacy:" +
                        "\nPharmacy id: 9338940" +
                        "\nName: Pokemon Center" +
                        "\nPhone: 3294485748" +
                        "\nEmail: frontnurse@pkcenter.net" +
                        "\nAddress: 234 Pewter Rd" +
                        "\nOpens: 3:00" +
                        "\nCloses: 23:00" +
                        "\n";
    }


}
