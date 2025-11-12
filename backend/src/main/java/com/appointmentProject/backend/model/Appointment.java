package com.appointmentProject.backend.model;
import com.appointmentProject.backend.util.NullString;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
 *          immutable. The number combination is created by the auto-increment feature in MySQL.
 *      - "patient_id": the id of the patient in the appointment
 *      - "provider_id": the id of the provider of the appointment
 *      - "nurse_id": (nullable) the id of the nurse involved in the appointment
 *      - "prescription_id": (nullable) the prescription written in the appointment
 *      - "billing_id": the id of the transaction resulting from the appointment
 *      - "lab_order_id": (nullable) the id of the lab order that the provider requested
 *      - "appointment_date": the date and time the appointment is scheduled
 *      - "room_number": the room number that the appointment will be at.
 *      - "start_time": (nullable) the actual start time of the appointment
 *      - "end_time" : (nullable) the actual end time of the appointment
 *      - "reason_for_visiting": the purpose of the appointment
 *
 * @author Matthew Kiyono
 * @version 1.3
 * @since 10/30/2026
 *******************************************************************************************/
@Entity
@Table(name = "appointment")
public class Appointment {

    //required variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id",  nullable = false)
    private int id;

    @NotNull
    @Column(name = "patient_id", nullable = false)
    private int patient_id;

    @NotNull
    @Column(name = "provider_id", nullable = false)
    private int provider_id;

    @NotNull
    @Column(name = "billing_id", nullable = false)
    private int billing_id;

    @NotNull
    @Column(name = "appointment_date", nullable = false, columnDefinition = "DATETIME(0)")
    private LocalDateTime appointment_date;

    @NotNull
    @Column(name = "room_number", nullable = false, length = 4)
    private String room_number;

    @NotNull
    @Column(name = "reason_for_visiting", nullable = false)
    private String reason_for_visiting;


    //optional variables
    @Column(name = "nurse_id")
    private Integer nurse_id;

    @Column(name = "prescription_id")
    private Integer prescription_id;

    @Column(name = "lab_order_id")
    private Integer lab_order_id;

    @Column(name = "start_time", columnDefinition = "TIME(0)")
    private LocalTime  start_time;

    @Column(name = "end_time", columnDefinition = "TIME(0)")
    private LocalTime end_time;

    //Test Constructor ONLY!
    protected Appointment() {}

    //Constructor
    private Appointment(Builder builder) {
        this.id = builder.id;
        this.patient_id = builder.patient_id;
        this.provider_id = builder.provider_id;
        this.billing_id = builder.billing_id;
        this.appointment_date = builder.appointment_date;
        this.room_number = builder.room_number;
        this.reason_for_visiting = builder.reason_for_visiting;
        this.nurse_id = builder.nurse_id;
        this.prescription_id = builder.prescription_id;
        this.lab_order_id = builder.lab_order_id;
        this.start_time = builder.start_time;
        this.end_time = builder.end_time;
    }

    //getter methods
    public int getId() {return id;}
    public int getPatient_id() {return patient_id;}
    public int getProvider_id() {return provider_id;}
    public int getBilling_id() {return billing_id;}
    public LocalDateTime getAppointment_date() {return appointment_date;}
    public String getRoom_number() {return room_number;}
    public String getReason_for_visiting() {return reason_for_visiting;}
    public Integer getNurse_id() {return nurse_id;}
    public Integer getPrescription_id() {return prescription_id;}
    public Integer getLab_order_id() {return lab_order_id;}
    public LocalTime getStart_time() {return start_time;}
    public LocalTime getEnd_time() {return end_time;}

    //setter methods
    public void setAppointment_date(LocalDateTime appointment_date) {this.appointment_date = appointment_date;}
    public void setId(int id) {this.id = id;}
    public void setPatient_id(int pT_id) {this.patient_id = pT_id;}
    public void setProvider_id(int pV_id) {this.provider_id = pV_id;}
    public void setBilling_id(int bL_id) {this.billing_id = bL_id;}
    public void setRoom_number(String room) {this.room_number = room;}
    public void setReason_for_visiting(String purpose) {this.reason_for_visiting = purpose;}
    public void setNurse_id(Integer nR_id) {this.nurse_id = nR_id;}
    public void setPrescription_id(Integer PR_id) {this.prescription_id = PR_id;}
    public void setLab_order_id(Integer LB_id) {this.lab_order_id = LB_id;}
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
        private int id;
        private int patient_id;
        private int provider_id;
        private int billing_id;
        private LocalDateTime appointment_date;
        private String room_number;
        private String reason_for_visiting;

        //optional variables
        private Integer nurse_id;
        private Integer prescription_id;
        private Integer lab_order_id;
        private LocalTime start_time;
        private LocalTime end_time;

        //required constructor only
        public Builder(int id, int patient_id, int provider_id, int billing_id, LocalDateTime appointment_date, String room_number, String purpose) {
            this.id = id;
            this.patient_id = patient_id;
            this.provider_id = provider_id;
            this.billing_id = billing_id;
            this.appointment_date = appointment_date;
            this.room_number = room_number;
            this.reason_for_visiting = purpose;
        }

        //method checks for optional variables
        public Builder nurse_id(Integer nurse_id) {
            this.nurse_id = nurse_id;
            return this;
        }
        public Builder prescription_id(Integer prescription_id) {
            this.prescription_id = prescription_id;
            return this;
        }
        public Builder lab_order_id(Integer lab_order_id) {
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
