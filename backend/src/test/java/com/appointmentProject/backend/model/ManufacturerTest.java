package com.appointmentProject.backend.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/***************************************************************************
 *   ManufacturerTest.java
 *
 *          This will test the Construction, getters, setters, and
 *          toString of the Manufacturer model.
 *          Prioritizes data integrity over logic in these tests.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 11/05/2025
 ****************************************************************************/

public class ManufacturerTest {

    @Test
    void testGetterAndSetter() {

        //Object Model
        String id1 = "MN45677654", id2 = "MN76544567";
        String name1 = "Bayer", name2 = "Johnson & Johnson";
        String phone1 = "5763229421", phone2 = "8845723398";
        String email1 = "frontDesk@bayer.com", email2 = "j&jmeds@johnson.org";
        String address1 = "123 Main St", address2 = "3490 Flint St";

        Manufacturer company = new Manufacturer(id1, name1, phone1, email1, address1);

        //getter tests
        assertAll(
                () -> assertEquals(id1, company.getId()),
                () -> assertEquals(name1, company.getManufacturer_name()),
                () -> assertEquals(phone1, company.getPhone()),
                () -> assertEquals(email1, company.getEmail()),
                () -> assertEquals(address1, company.getAddress())
                );

        //setter tests
        company.setId(id2);
        company.setManufacturer_name(name2);
        company.setPhone(phone2);
        company.setEmail(email2);
        company.setAddress(address2);

        assertAll(
                () -> assertEquals(id2, company.getId()),
                () -> assertEquals(name2, company.getManufacturer_name()),
                () -> assertEquals(phone2, company.getPhone()),
                () -> assertEquals(email2, company.getEmail()),
                () -> assertEquals(address2, company.getAddress())
        );
    }

    @Test
    void testToString() {
        Manufacturer company = new Manufacturer("MN65783399", "Roche",
                "5990972939", "rcompany@roche.org", "910 W Navy Ave");

        String expected =
                "Manufacturer:\n" +
                        "\nMN id: MN65783399" +
                        "\n manufacturer name: Roche" +
                        "\n phone: 5990972939" +
                        "\n email: rcompany@roche.org" +
                        "\n address: 910 W Navy Ave" + "\n";

        assertEquals(expected, company.toString());
    }
}
