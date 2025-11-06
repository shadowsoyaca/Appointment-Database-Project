package com.appointmentProject.backend.model;
import com.appointmentProject.backend.util.NullString;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/***************************************************************************
 *   BillingTest.java
 *
 *          This will test the Construction, getters, setters, and
 *          toString of the Billing model.
 *          Prioritizes data integrity over logic in these tests.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 11/05/2025
 ****************************************************************************/
public class BillingTest {

    //getter and setter tests
    @Test
    void testGetterAndSetter() {
        String id1 = "BL22334455", id2 = "BL09920375";
        double cost1 = 90.75, cost2 = 54.85;
        String status1 = "PAID", status2 = "PENDING";
        String payType1 = "Credit",  payType2 = "Debit";
        String insuranceID1 = "IN35790864", insuranceID2 = null;

        Billing bill = new Billing.Builder(
                id1, cost1, status1, payType1).insurance_id(insuranceID1).build();


        //getter tests

        assertAll(
                () -> assertEquals(id1, bill.getId()),
                () -> assertEquals(cost1,bill.getCost()),
                () -> assertEquals(status1, bill.getStatus_of_payment()),
                () -> assertEquals(payType1, bill.getPayment_type()),
                () -> assertEquals(insuranceID1, bill.getInsurance_id())
        );

        //setter tests
        bill.setId(id2);
        bill.setCost(cost2);;
        bill.setStatus_of_payment(status2);
        bill.setPayment_type(payType2);
        bill.setInsurance_id(insuranceID2);

        assertAll(
                () -> assertEquals(id2, bill.getId()),
                () -> assertEquals(cost2,bill.getCost()),
                () -> assertEquals(status2, bill.getStatus_of_payment()),
                () -> assertEquals(payType2, bill.getPayment_type()),
                () -> assertNull(bill.getInsurance_id())
        );
    }

    @Test
    void testToString() {
        Billing bill = new Billing.Builder(
                "BL75767877", 320.45, "INCOMPLETE",
                "N/A").insurance_id("IN77777777").build();

        String expectedA =
                "Billing: " +
                        "\nTransaction ID: BL75767877" +
                        "\nTotal Cost: 320.45" +
                        "\nPayment Status: INCOMPLETE" +
                        "\nPayment Type: N/A" +
                        "\nInsurance ID: IN77777777" + "\n";

        assertEquals(expectedA, bill.toString());

        //NullString Interaction Test
        bill.setInsurance_id(null);

        String expectedB =
                "Billing: " +
                        "\nTransaction ID: BL75767877" +
                        "\nTotal Cost: 320.45" +
                        "\nPayment Status: INCOMPLETE" +
                        "\nPayment Type: N/A" +
                        "\nInsurance ID: N/A" + "\n";
    }
}
