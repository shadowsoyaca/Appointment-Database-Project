package com.appointmentProject.backend.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/***************************************************************************
 *   MedicationTest.java
 *
 *          This will test the Construction, getters, setters, and
 *          toString of the Billing model.
 *          Prioritizes data integrity over logic in these tests.
 *
 * @author Matthew Kiyono
 * @version 1.1
 * @since 11/05/2025
 ****************************************************************************/
public class MedicationTest {

    @Test
    void testGetterAndSetter() {

        //Test Object
        int id1 = 24798653, id2 = 35689742;
        String name1 = "Advil", name2 = "Tylenol";
        int manufacturer1 = 44557700, manufacturer2 = 3;
        double strength1 = 2.5,  strength2 = 1.5;
        String type1 = "Pill", type2 = "Tablet";
        String method1 = "Oral", method2 = "Oral";

        Medication med = new Medication(id1, name1, manufacturer1, strength1, type1, method1);

        //getter test
        assertAll(
                () -> assertEquals(id1, med.getId()),
                () -> assertEquals(name1, med.getMedName()),
                () -> assertEquals(manufacturer1, med.getManufacturer_id()),
                () -> assertEquals(strength1, med.getStrength()),
                () -> assertEquals(type1, med.getType()),
                () -> assertEquals(method1, med.getConsumption())
        );

        //setter test
        med.setId(id2);
        med.setMedName(name2);
        med.setManufacturer_id(manufacturer2);
        med.setStrength(strength2);
        med.setType(type2);
        med.setConsumption(method2);

        assertAll(
                () -> assertEquals(id2, med.getId()),
                () -> assertEquals(name2, med.getMedName()),
                () -> assertEquals(manufacturer2, med.getManufacturer_id()),
                () -> assertEquals(strength2, med.getStrength()),
                () -> assertEquals(type2, med.getType()),
                () -> assertEquals(method2, med.getConsumption())
        );
    }

    @Test
    void testToString() {

        //Test Object
        Medication med = new Medication(35579904, "Motelukast",
                23098732, 5.5, "Tablet", "Oral" );

        String expectation =
            "Medication:" +
                "\nMed ID: 35579904" +
                "\nMed Name: Motelukast" +
                "\nManufacturer id: 23098732" +
                "\nStrength: 5.5" +
                "\nType of Medication: Tablet" +
                "\nConsumption Method: Oral"  +
                "\n";

        assertEquals(expectation, med.toString());
    }
}
