package com.appointmentProject.backend.model;
import com.appointmentProject.backend.util.NullString;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;

/***************************************************************************************
 * Appointment.java
 *
 *      This is a blueprint for storing information related to Appointment and acts as
 *      a model to transfer information between the backend and the frontend.
 *
 *      Contains identifying variables of Appointment and is used by the service layer
 *      for retrieval and updates.
 *      - "id": is a unique identifier that differentiates between Appointments. It is
 *          also the primary way of accessing specific Appointment records. This is
 *          immutable and leads with an "AP". The number combination is created by the
 *          auto-increment feature in MySQL.
 *      - "patient_id": the id of the patient in the appointment (must have PT in front)
 *      - "provider_id": the id of the provider of the appointment (must have PV in front)
 *      - "nurse_id": (nullable) the id of the nurse involved in the appointment
 *      - "prescription_id": (nullable) the prescription written in the appointment
 *      - "billing_id": the id of the transaction resulting from the appointment
 *      - "lab_order_id": (nullable) the id of the lab order that the provider requested
 *      - "appointment_date": the date and time the appointment is scheduled
 *      - "appointment_length": the expected length of time for the appointment
 *      - "room_number": the room number that the appointment will be at.
 *      - "start_time": (nullable) the actual start time of the appointment
 *      - "end_time" : (nullable) the actual end time of the appointment
 *      - "reason_for_visiting": the purpose of the appointment
 *
 * @author Matthew Kiyono
 * @version 1.2
 * @since 10/30/2026
 *******************************************************************************************/
public class Appointment {

    //required variables
    private String id;
    private String patient_id;
    private String provider_id;
    private String billing_id;
    private LocalDateTime appointment_date;
    private Duration appointment_length;
    private int room_number;
    private String reason_for_visiting;

    //optional variables
    private String nurse_id;
    private String prescription_id;
    private String lab_order_id;
    private LocalTime  start_time;
    private LocalTime end_time;

    //Constructor
    private Appointment(Builder builder) {
        this.id = builder.id;
        this.patient_id = builder.patient_id;
        this.provider_id = builder.provider_id;
        this.billing_id = builder.billing_id;
        this.appointment_date = builder.appointment_date;
        this.appointment_length = builder.appointment_length;
        this.room_number = builder.room_number;
        this.reason_for_visiting = builder.reason_for_visiting;
        this.nurse_id = builder.nurse_id;
        this.prescription_id = builder.prescription_id;
        this.lab_order_id = builder.lab_order_id;
        this.start_time = builder.start_time;
        this.end_time = builder.end_time;
    }

    //getter methods
    public String getId() {return id;}
    public String getPatient_id() {return patient_id;}
    public String getProvider_id() {return provider_id;}
    public String getBilling_id() {return billing_id;}
    public LocalDateTime getAppointment_date() {return appointment_date;}
    public Duration getAppointment_length() {return appointment_length;}
    public int getRoom_number() {return room_number;}
    public String getReason_for_visiting() {return reason_for_visiting;}
    public String getNurse_id() {return nurse_id;}
    public String getPrescription_id() {return prescription_id;}
    public String getLab_order_id() {return lab_order_id;}
    public LocalTime getStart_time() {return start_time;}
    public LocalTime getEnd_time() {return end_time;}

    //setter methods
    public void setAppointment_date(LocalDateTime appointment_date) {this.appointment_date = appointment_date;}
    public void setId(String id) {this.id = id;}
    public void setPT_id(String pT_id) {this.patient_id = pT_id;}
    public void setPV_id(String pV_id) {this.provider_id = pV_id;}
    public void setBL_id(String bL_id) {this.billing_id = bL_id;}
    public void setLength(Duration length) {this.appointment_length = length;}
    public void setRoom(int room) {this.room_number = room;}
    public void setPurpose(String purpose) {this.reason_for_visiting = purpose;}
    public void setNR_id(String nR_id) {this.nurse_id = nR_id;}
    public void setPR_id(String PR_id) {this.prescription_id = PR_id;}
    public void setLB_id(String LB_id) {this.lab_order_id = LB_id;}
    public void setStart_time(LocalTime  start_time) {this.start_time = start_time;}
    public void setEnd_time(LocalTime end_time) {this.end_time = end_time;}

    //toString
    @Override
    public String toString() {
        return "Appointment: " +
                "\nAppointment ID: " + id +
                "\nPatient ID: " + patient_id +
                "\nProvider ID: " + provider_id +
                "\nNurse ID: " + NullString.check(nurse_id) +
                "\nBilling ID: " + billing_id +
                "\nScheduled Appointment: " + appointment_date +
                "\nLength of Appointment: " + appointment_length +
                "\nAppointment Start Time: " + NullString.check(start_time) +
                "\nAppointment End Time: " + NullString.check(end_time) +
                "\nRoom Number: " + room_number +
                "\nPrescription ID: " + NullString.check(prescription_id) +
                "\nLab Order ID: " + NullString.check(lab_order_id) +
                "\nPurpose of Appointment: " + reason_for_visiting + "\n";
    }

    //Builder Constructor
    public static class Builder {

        //required variables
        private String id;
        private String patient_id;
        private String provider_id;
        private String billing_id;
        private LocalDateTime appointment_date;
        private Duration appointment_length;
        private int room_number;
        private String reason_for_visiting;

        //optional variables
        private String nurse_id;
        private String prescription_id;
        private String lab_order_id;
        private LocalTime start_time;
        private LocalTime end_time;

        //required constructor only
        public Builder(String id, String patient_id, String provider_id, String billing_id, LocalDateTime appointment_date, Duration appointment_length, int room_number, String purpose) {
            this.id = id;
            this.patient_id = patient_id;
            this.provider_id = provider_id;
            this.billing_id = billing_id;
            this.appointment_date = appointment_date;
            this.appointment_length = appointment_length;
            this.room_number = room_number;
            this.reason_for_visiting = purpose;
        }

        //method checks for optional variables
        public Builder nurse_id(String nurse_id) {
            this.nurse_id = nurse_id;
            return this;
        }
        public Builder prescription_id(String prescription_id) {
            this.prescription_id = prescription_id;
            return this;
        }
        public Builder lab_order_id(String lab_order_id) {
            this.lab_order_id = lab_order_id;
            return this;
        }
        public Builder start_time(LocalTime start_time) {
            this.start_time = start_time;
            return this;
        }
        public Builder end_time(LocalTime end_time) {
            this.end_time = end_time;
            return this;
        }

        //Combine the required and the provided optional variables
        public Appointment build(){return new Appointment(this);}
    }
}
