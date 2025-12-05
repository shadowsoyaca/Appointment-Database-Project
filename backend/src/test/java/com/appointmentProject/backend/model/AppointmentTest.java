package com.appointmentProject.backend.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalTime;
import java.time.LocalDateTime;


/***************************************************************************
 *   AppointmentTest.java
 *
 *          This will test the Construction, getters, setters, and
 *          toString of the Appointment model.
 *          Prioritizes data integrity over logic in these tests.
 *
 * @author Matthew Kiyono
 * @version 1.1
 * @since 11/05/2025
 ****************************************************************************/
public class AppointmentTest {

    @Test
    void testGetterAndSetter(){

        //Test Model
            //REQUIRED
        int id1 = 12345678, id2 = 98765432;
        int patient_id1 = 12345098, patient_id2 = 98700000;
        int provider_id1 = 12345432,  provider_id2 = 78659401;
        int billing_id1 = 9,  billing_id2 = 10;
        LocalDateTime date1 = LocalDateTime.now(), date2 = LocalDateTime.parse("1996-03-22T12:30");
        String room1 = "123", room2 = "456";
        String purpose1 = "Physical", purpose2 = "Flu Shot";
            //OPTIONAL
        Integer nurse1 = 54328099, nurse2 = null;
        Integer prescipt1 = 55603944,  prescipt2 = null;
        Integer lab1 = 43216789, lab2 = null;
        LocalTime start1 =  LocalTime.parse("12:30"), start2 = null;
        LocalTime end1 =  LocalTime.parse("13:30"), end2 = null;

        Appointment appt = new Appointment.Builder(
                id1,
                patient_id1,
                provider_id1,
                billing_id1,
                date1,
                room1,
                purpose1
        )
                .nurseId(nurse1)
                .prescriptionId(prescipt1)
                .labOrderId(lab1)
                .startTime(start1)
                .endTime(end1)
                .build();
        assertAll(
                //getter test
                () -> assertEquals(id1, appt.getId()),
                () -> assertEquals(patient_id1, appt.getPatientId()),
                () -> assertEquals(provider_id1, appt.getProviderId()),
                () -> assertEquals(billing_id1, appt.getBillingId()),
                () -> assertEquals(date1, appt.getAppointmentDate()),
                () -> assertEquals(room1, appt.getRoomNumber()),
                () -> assertEquals(purpose1, appt.getReasonForVisiting()),
                () -> assertEquals(nurse1, appt.getNurseId()),
                () -> assertEquals(prescipt1, appt.getPrescriptionId()),
                () -> assertEquals(lab1, appt.getLabOrderId()),
                () -> assertEquals(start1, appt.getStartTime()),
                () -> assertEquals(end1, appt.getEndTime())
        );

        //Setter Test
        appt.setId(id2);
        appt.setPatientId(patient_id2);
        appt.setProviderId(provider_id2);
        appt.setBillingId(billing_id2);
        appt.setAppointmentDate(date2);
        appt.setRoomNumber(room2);
        appt.setReasonForVisiting(purpose2);
        appt.setNurseId(nurse2);
        appt.setPrescriptionId(prescipt2);
        appt.setLabOrderId(lab2);
        appt.setStartTime(start2);
        appt.setEndTime(end2);

        assertAll(
                () -> assertEquals(id2, appt.getId()),
                () -> assertEquals(patient_id2, appt.getPatientId()),
                () -> assertEquals(provider_id2, appt.getProviderId()),
                () -> assertEquals(billing_id2, appt.getBillingId()),
                () -> assertEquals(date2, appt.getAppointmentDate()),
                () -> assertEquals(room2, appt.getRoomNumber()),
                () -> assertEquals(purpose2, appt.getReasonForVisiting()),
                () -> assertNull(appt.getNurseId()),
                () -> assertNull(appt.getPrescriptionId()),
                () -> assertNull(appt.getLabOrderId()),
                () -> assertNull(appt.getStartTime()),
                () -> assertNull(appt.getEndTime())
        );
    }

    //Tests the toString and the NullString Utility
    @Test
    void testToString() {


        //Test Model
        //REQUIRED
        int id1 = 12345678, id2 = 98765432;
        int patient_id1 = 12345098, patient_id2 = 98700000;
        int provider_id1 = 12345432, provider_id2 = 78659401;
        int billing_id1 = 9, billing_id2 = 10;
        LocalDateTime date1 = LocalDateTime.now(), date2 = LocalDateTime.parse("1996-03-22T12:30");
        String room1 = "123", room2 = "456";
        String purpose1 = "Physical", purpose2 = "Flu Shot";
        //OPTIONAL
        Integer nurse1 = 54328099, nurse2 = null;
        Integer prescipt1 = 55603944, prescipt2 = null;
        Integer lab1 = 43216789, lab2 = null;
        LocalTime start1 = LocalTime.parse("12:30"), start2 = null;
        LocalTime end1 = LocalTime.parse("13:30"), end2 = null;

        Appointment appt = new Appointment.Builder(
                id1,
                patient_id1,
                provider_id1,
                billing_id1,
                date2,
                room1,
                purpose1
        )
                .nurseId(nurse1)
                .prescriptionId(prescipt1)
                .labOrderId(lab1)
                .startTime(start1)
                .endTime(end1)
                .build();
        //Normal Print
        String expectedA =
                "Appointment: " +
                        "\nAppointment ID: 12345678" +
                        "\nPatient ID: 12345098" +
                        "\nProvider ID: 12345432" +
                        "\nNurse ID: 54328099" +
                        "\nBilling ID: 9" +
                        "\nScheduled Appointment: 1996-03-22T12:30" +
                        "\nAppointment Start Time: 12:30" +
                        "\nAppointment End Time: 13:30" +
                        "\nRoom Number: 123" +
                        "\nPrescription ID: 55603944" +
                        "\nLab Order ID: 43216789" +
                        "\nPurpose of Appointment: Physical" + "\n";

        assertEquals(expectedA, appt.toString());

        //Null String Involved Print
        appt.setNurseId(nurse2);
        appt.setPrescriptionId(prescipt2);
        appt.setLabOrderId(lab2);
        appt.setStartTime(start2);
        appt.setEndTime(end2);

        String expectedB =
                "Appointment: " +
                        "\nAppointment ID: 12345678" +
                        "\nPatient ID: 12345098" +
                        "\nProvider ID: 12345432" +
                        "\nNurse ID: N/A" +
                        "\nBilling ID: 9" +
                        "\nScheduled Appointment: 1996-03-22T12:30" +
                        "\nAppointment Start Time: N/A" +
                        "\nAppointment End Time: N/A" +
                        "\nRoom Number: 123" +
                        "\nPrescription ID: N/A" +
                        "\nLab Order ID: N/A" +
                        "\nPurpose of Appointment: Physical" + "\n";

        assertEquals(expectedB, appt.toString());
    }
}
