package com.appointmentProject.backend.model;
import com.appointmentProject.backend.util.NullString;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/***************************************************************************
 *   PrescriptionTest.java
 *
 *          This will test the Construction, getters, setters, and
 *          toString of the Prescription model.
 *          Prioritizes data integrity over logic in these tests.
 *
 * @author Matthew Kiyono
 * @version 1.1
 * @since 11/06/2025
 ****************************************************************************/
public class PrescriptionTest {

    @Test
    void testGetterAndSetter() {

        //Test Object
        int id1 = 23456098, id2 = 34958304;
        int MD_id1 = 23894738, MD_id2 = 21199338;
        int PH_id1 = 24848990, PH_id2 = 24848991;
        int quantity1 = 30, quantity2 = 14;
        int freq1 = 1, freq2 = 2;
        LocalDate start1 = LocalDate.of(2024, 1, 23),
                start2 = LocalDate.of(2024, 2, 20);
        double price1 = 60.30, price2 = 125.00;
        String status1 = "Received", status2 = "Processing";
        Integer IN_id1 = 22443300,  IN_id2 = null;
        LocalDate end1 = LocalDate.of(2024, 2, 23),
                end2 = null;
        LocalDateTime receive1 = LocalDateTime.of(2024, 1, 23, 11, 20),
                receive2 = null;

        Prescription order = new Prescription.Builder(
                id1, MD_id1, PH_id1, quantity1, freq1, start1, price1, status1)
                .insurance_id(IN_id1).end_date(end1).date_picked_up(receive1).build();

        //getter Test

        assertAll(
                () -> assertEquals(id1,order.getId()),
                () -> assertEquals(MD_id1,order.getMedication_id()),
                () -> assertEquals(PH_id1,order.getPharmacy_id()),
                () -> assertEquals(quantity1,order.getQuantity()),
                () -> assertEquals(freq1,order.getFrequency()),
                () -> assertEquals(start1,order.getStartDate()),
                () -> assertEquals(price1,order.getPrice()),
                () -> assertEquals(status1,order.getStatus()),
                () -> assertEquals(IN_id1,order.getInsurance_id()),
                () -> assertEquals(end1,order.getEndDate()),
                () -> assertEquals(receive1,order.getDatePickedUp())
        );

        //setter Test
        order.setId(id2);
        order.setMedication_id(MD_id2);
        order.setPharmacy_id(PH_id2);
        order.setQuantity(quantity2);
        order.setFrequency(freq2);
        order.setStartDate(start2);
        order.setPrice(price2);
        order.setStatus(status2);
        order.setInsurance_id(IN_id2);
        order.setEndDate(end2);
        order.setDate_picked_up(receive2);

        assertAll(
                () -> assertEquals(id2,order.getId()),
                () -> assertEquals(MD_id2,order.getMedication_id()),
                () -> assertEquals(PH_id2,order.getPharmacy_id()),
                () -> assertEquals(quantity2,order.getQuantity()),
                () -> assertEquals(freq2,order.getFrequency()),
                () -> assertEquals(start2,order.getStartDate()),
                () -> assertEquals(price2,order.getPrice()),
                () -> assertEquals(status2,order.getStatus()),
                () -> assertNull(order.getInsurance_id()),
                () -> assertNull(order.getEndDate()),
                () -> assertNull(order.getDatePickedUp())
        );
    }

    @Test
    void testToString() {

        //Test Object
        Prescription order = new Prescription.Builder(
                33578400, 23122332, 11122345, 52,
                2, LocalDate.of(2025, 10, 12), 200.00,
                "RECEIVED").insurance_id(57648802)
                .end_date(LocalDate.of(2025, 11, 7))
                .date_picked_up(LocalDateTime.of(2025, 10, 12, 13, 22)).build();

        String expectedA =
                "Prescription: " +
                        "\nPrescription ID: 33578400" +
                        "\nMedication ID: 23122332" +
                        "\nPharmacy ID: 11122345" +
                        "\nQuantity: 52" +
                        "\nFrequency: 2 daily" +
                        "\nPrescribed Date: 2025-10-12" +
                        "\nPrice: 200.0" +
                        "\nStatus: RECEIVED" +
                        "\nInsurance ID: 57648802" +
                        "\nEnd Date: 2025-11-07" +
                        "\nDate Picked Up: 2025-10-12T13:22" +
                        "\n";

        assertEquals(expectedA, order.toString());

        //NullString Interaction Test
        order.setStatus("Ready for Pickup");
        order.setEndDate(null);
        order.setInsurance_id(null);
        order.setDate_picked_up(null);

        String expectedB =
                "Prescription: " +
                        "\nPrescription ID: 33578400" +
                        "\nMedication ID: 23122332" +
                        "\nPharmacy ID: 11122345" +
                        "\nQuantity: 52" +
                        "\nFrequency: 2 daily" +
                        "\nPrescribed Date: 2025-10-12" +
                        "\nPrice: 200.0" +
                        "\nStatus: Ready for Pickup" +
                        "\nInsurance ID: N/A" +
                        "\nEnd Date: N/A" +
                        "\nDate Picked Up: N/A" +
                        "\n";

        assertEquals(expectedB, order.toString());

    }

}
