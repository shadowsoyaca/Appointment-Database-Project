package com.appointmentProject.backend.model;
import com.appointmentProject.backend.util.NullString;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.appointmentProject.backend.model.Appointment;
import com.appointmentProject.backend.model.Appointment.Builder;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

/***************************************************************************
 *   AppointmentTest.java
 *
 *          This will test the Construction, getters, setters, and
 *          toString of the Appointment model.
 *          Prioritizes data integrity over logic in these tests.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 11/05/2025
 ****************************************************************************/
public class AppointmentTest {

    @Test
    void testGetterAndSetter(){



        //Test Model
            //REQUIRED
        String id1 = "AP12345678", id2 = "AP98765432";
        String patient_id1 = "PT12345098", patient_id2 = "PT98700000";
        String provider_id1 = "PR12345432",  provider_id2 = "PR78659401";
        String billing_id1 = "BL00000009",  billing_id2 = "BL00000010";
        LocalDateTime date1 = LocalDateTime.now(), date2 = LocalDateTime.parse("1996-03-22T12:30");
        Duration time1 =Duration.ofHours(1), time2 = Duration.ofMinutes(30);
        int room1 = 123, room2 = 456;
        String purpose1 = "Physical", purpose2 = "Flu Shot";
            //OPTIONAL
        String nurse1 = "NR54328099", nurse2 = null;
        String prescipt1 = "PR55603944",  prescipt2 = null;
        String lab1 = "LB43216789", lab2 = null;
        LocalTime start1 =  LocalTime.parse("12:30"), start2 = null;
        LocalTime end1 =  LocalTime.parse("13:30"), end2 = null;

        Appointment appt = new Appointment.Builder(
                id1,
                patient_id1,
                provider_id1,
                billing_id1,
                date1,
                time1,
                room1,
                purpose1
        )
                .nurse_id(nurse1)
                .prescription_id(prescipt1)
                .lab_order_id(lab1)
                .start_time(start1)
                .end_time(end1)
                .build();
        assertAll(
                //getter test
                () -> assertEquals(id1, appt.getId()),
                () -> assertEquals(patient_id1, appt.getPatient_id()),
                () -> assertEquals(provider_id1, appt.getProvider_id()),
                () -> assertEquals(billing_id1, appt.getBilling_id()),
                () -> assertEquals(date1, appt.getAppointment_date()),
                () -> assertEquals(time1, appt.getAppointment_length()),
                () -> assertEquals(room1, appt.getRoom_number()),
                () -> assertEquals(purpose1, appt.getReason_for_visiting()),
                () -> assertEquals(nurse1, appt.getNurse_id()),
                () -> assertEquals(prescipt1, appt.getPrescription_id()),
                () -> assertEquals(lab1, appt.getLab_order_id()),
                () -> assertEquals(start1, appt.getStart_time()),
                () -> assertEquals(end1, appt.getEnd_time())
        );

        //Setter Test
        appt.setId(id2);
        appt.setPT_id(patient_id2);
        appt.setPV_id(provider_id2);
        appt.setBL_id(billing_id2);
        appt.setAppointment_date(date2);
        appt.setLength(time2);
        appt.setRoom(room2);
        appt.setPurpose(purpose2);
        appt.setNR_id(nurse2);
        appt.setPR_id(prescipt2);
        appt.setLB_id(lab2);
        appt.setStart_time(start2);
        appt.setEnd_time(end2);

        assertAll(
                () -> assertEquals(id2, appt.getId()),
                () -> assertEquals(patient_id2, appt.getPatient_id()),
                () -> assertEquals(provider_id2, appt.getProvider_id()),
                () -> assertEquals(billing_id2, appt.getBilling_id()),
                () -> assertEquals(date2, appt.getAppointment_date()),
                () -> assertEquals(time2, appt.getAppointment_length()),
                () -> assertEquals(room2, appt.getRoom_number()),
                () -> assertEquals(purpose2, appt.getReason_for_visiting()),
                () -> assertNull(appt.getNurse_id()),
                () -> assertNull(appt.getPrescription_id()),
                () -> assertNull(appt.getLab_order_id()),
                () -> assertNull(appt.getStart_time()),
                () -> assertNull(appt.getEnd_time())
        );
    }

    //Tests the toString and the NullString Utility
    @Test
    void testToString() {


        //Test Model
        //REQUIRED
        String id1 = "AP12345678", id2 = "AP98765432";
        String patient_id1 = "PT12345098", patient_id2 = "PT98700000";
        String provider_id1 = "PR12345432", provider_id2 = "PR78659401";
        String billing_id1 = "BL00000009", billing_id2 = "BL00000010";
        LocalDateTime date1 = LocalDateTime.now(), date2 = LocalDateTime.parse("1996-03-22T12:30");
        Duration time1 = Duration.ofHours(1), time2 = Duration.ofMinutes(30);
        int room1 = 123, room2 = 456;
        String purpose1 = "Physical", purpose2 = "Flu Shot";
        //OPTIONAL
        String nurse1 = "NR54328099", nurse2 = null;
        String prescipt1 = "PR55603944", prescipt2 = null;
        String lab1 = "LB43216789", lab2 = null;
        LocalTime start1 = LocalTime.parse("12:30"), start2 = null;
        LocalTime end1 = LocalTime.parse("13:30"), end2 = null;

        Appointment appt = new Appointment.Builder(
                id1,
                patient_id1,
                provider_id1,
                billing_id1,
                date2,
                time1,
                room1,
                purpose1
        )
                .nurse_id(nurse1)
                .prescription_id(prescipt1)
                .lab_order_id(lab1)
                .start_time(start1)
                .end_time(end1)
                .build();
        //Normal Print
        String expectedA =
                "Appointment: " +
                        "\nAppointment ID: AP12345678" +
                        "\nPatient ID: PT12345098" +
                        "\nProvider ID: PR12345432" +
                        "\nNurse ID: NR54328099" +
                        "\nBilling ID: BL00000009" +
                        "\nScheduled Appointment: 1996-03-22T12:30" +
                        "\nLength of Appointment: PT1H" +
                        "\nAppointment Start Time: 12:30" +
                        "\nAppointment End Time: 13:30" +
                        "\nRoom Number: 123" +
                        "\nPrescription ID: PR55603944" +
                        "\nLab Order ID: LB43216789" +
                        "\nPurpose of Appointment: Physical" + "\n";

        assertEquals(expectedA, appt.toString());

        //Null String Involved Print
        appt.setNR_id(nurse2);
        appt.setPR_id(prescipt2);
        appt.setLB_id(lab2);
        appt.setStart_time(start2);
        appt.setEnd_time(end2);

        String expectedB =
                "Appointment: " +
                        "\nAppointment ID: AP12345678" +
                        "\nPatient ID: PT12345098" +
                        "\nProvider ID: PR12345432" +
                        "\nNurse ID: N/A" +
                        "\nBilling ID: BL00000009" +
                        "\nScheduled Appointment: 1996-03-22T12:30" +
                        "\nLength of Appointment: PT1H" +
                        "\nAppointment Start Time: N/A" +
                        "\nAppointment End Time: N/A" +
                        "\nRoom Number: 123" +
                        "\nPrescription ID: N/A" +
                        "\nLab Order ID: N/A" +
                        "\nPurpose of Appointment: Physical" + "\n";

        assertEquals(expectedB, appt.toString());
    }
}
