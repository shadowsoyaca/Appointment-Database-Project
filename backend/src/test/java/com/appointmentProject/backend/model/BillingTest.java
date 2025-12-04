package com.appointmentProject.backend.model;
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
 * @version 1.1
 * @since 11/05/2025
 ****************************************************************************/
public class BillingTest {

    //getter and setter tests
    @Test
    void testGetterAndSetter() {
        int id1 = 22334455, id2 = 9920375;
        double cost1 = 90.75, cost2 = 54.85;
        String status1 = "PAID", status2 = "PENDING";
        String payType1 = "Credit",  payType2 = "Debit";
        Integer insuranceID1 = 35790864, insuranceID2 = null;

        Billing bill = new Billing.Builder(
                id1, cost1, status1, payType1).insuranceId(insuranceID1).build();


        //getter tests

        assertAll(
                () -> assertEquals(id1, bill.getId()),
                () -> assertEquals(cost1,bill.getCost()),
                () -> assertEquals(status1, bill.getStatusOfPayment()),
                () -> assertEquals(payType1, bill.getPaymentType()),
                () -> assertEquals(insuranceID1, bill.getInsuranceId())
        );

        //setter tests
        bill.setId(id2);
        bill.setCost(cost2);;
        bill.setStatusOfPayment(status2);
        bill.setPaymentType(payType2);
        bill.setInsuranceId(insuranceID2);

        assertAll(
                () -> assertEquals(id2, bill.getId()),
                () -> assertEquals(cost2,bill.getCost()),
                () -> assertEquals(status2, bill.getStatusOfPayment()),
                () -> assertEquals(payType2, bill.getPaymentType()),
                () -> assertNull(bill.getInsuranceId())
        );
    }

    @Test
    void testToString() {
        Billing bill = new Billing.Builder(
                75767877, 320.45, "INCOMPLETE",
                "N/A").insuranceId(77777777).build();

        String expectedA =
                "Billing: " +
                        "\nTransaction ID: 75767877" +
                        "\nTotal Cost: 320.45" +
                        "\nPayment Status: INCOMPLETE" +
                        "\nPayment Type: N/A" +
                        "\nInsurance ID: 77777777" + "\n";

        assertEquals(expectedA, bill.toString());

        //NullString Interaction Test
        bill.setInsuranceId(null);

        String expectedB =
                "Billing: " +
                        "\nTransaction ID: BL75767877" +
                        "\nTotal Cost: 320.45" +
                        "\nPayment Status: INCOMPLETE" +
                        "\nPayment Type: N/A" +
                        "\nInsurance ID: N/A" + "\n";
    }
}
