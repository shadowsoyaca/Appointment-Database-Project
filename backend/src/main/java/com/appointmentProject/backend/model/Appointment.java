package com.appointmentProject.backend.model;
import com.appointmentProject.backend.util.NullString;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicUpdate
public class Appointment {

    //required variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id",  nullable = false)
    private int id;

    @NotNull
    @Column(name = "patientId", nullable = false)
    private int patientId;

    @NotNull
    @Column(name = "providerId", nullable = false)
    private int providerId;

    @NotNull
    @Column(name = "billingId", nullable = false)
    private int billingId;

    @NotNull
    @Column(name = "appointmentDate", nullable = false, columnDefinition = "DATETIME(0)")
    private LocalDateTime appointmentDate;

    @NotNull
    @Column(name = "roomNumber", nullable = false, length = 4)
    private String roomNumber;

    @NotNull
    @Column(name = "reasonForVisiting", nullable = false)
    private String reasonForVisiting;


    //optional variables
    @Column(name = "nurseId")
    private Integer nurseId;

    @Column(name = "prescriptionId")
    private Integer prescriptionId;

    @Column(name = "labOrderId")
    private Integer labOrderId;

    @Column(name = "startTime", columnDefinition = "TIME(0)")
    private LocalTime  startTime;

    @Column(name = "endTime", columnDefinition = "TIME(0)")
    private LocalTime endTime;

    //Test Constructor ONLY!
    protected Appointment() {}

    //Constructor
    private Appointment(Builder builder) {
        this.id = builder.id;
        this.patientId = builder.patientId;
        this.providerId = builder.providerId;
        this.billingId = builder.billingId;
        this.appointmentDate = builder.appointmentDate;
        this.roomNumber = builder.roomNumber;
        this.reasonForVisiting = builder.reasonForVisiting;
        this.nurseId = builder.nurseId;
        this.prescriptionId = builder.prescriptionId;
        this.labOrderId = builder.labOrderId;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    //getter methods
    public int getId() {return id;}
    public int getPatientId() {return patientId;}
    public int getProviderId() {return providerId;}
    public int getBillingId() {return billingId;}
    public LocalDateTime getAppointmentDate() {return appointmentDate;}
    public String getRoomNumber() {return roomNumber;}
    public String getReasonForVisiting() {return reasonForVisiting;}
    public Integer getNurseId() {return nurseId;}
    public Integer getPrescriptionId() {return prescriptionId;}
    public Integer getLabOrderId() {return labOrderId;}
    public LocalTime getStartTime() {return startTime;}
    public LocalTime getEndTime() {return endTime;}

    //setter methods
    public void setAppointmentDate(LocalDateTime appointmentDate) {this.appointmentDate = appointmentDate;}
    public void setId(int id) {this.id = id;}
    public void setPatientId(int ptId) {this.patientId = ptId;}
    public void setProviderId(int pvId) {this.providerId = pvId;}
    public void setBillingId(int blId) {this.billingId = blId;}
    public void setRoomNumber(String room) {this.roomNumber = room;}
    public void setReasonForVisiting(String purpose) {this.reasonForVisiting = purpose;}
    public void setNurseId(Integer nrId) {this.nurseId = nrId;}
    public void setPrescriptionId(Integer prId) {this.prescriptionId = prId;}
    public void setLabOrderId(Integer lbId) {this.labOrderId = lbId;}
    public void setStartTime(LocalTime  startTime) {this.startTime = startTime;}
    public void setEndTime(LocalTime endTime) {this.endTime = endTime;}

    //toString
    @Override
    public String toString() {
        return "Appointment: " +
                "\nAppointment ID: " + id +
                "\nPatient ID: " + patientId +
                "\nProvider ID: " + providerId +
                "\nNurse ID: " + NullString.check(nurseId) +
                "\nBilling ID: " + billingId +
                "\nScheduled Appointment: " + appointmentDate +
                "\nAppointment Start Time: " + NullString.check(startTime) +
                "\nAppointment End Time: " + NullString.check(endTime) +
                "\nRoom Number: " + roomNumber +
                "\nPrescription ID: " + NullString.check(prescriptionId) +
                "\nLab Order ID: " + NullString.check(labOrderId) +
                "\nPurpose of Appointment: " + reasonForVisiting + "\n";
    }

    //Builder Constructor
    public static class Builder {

        //required variables
        private int id;
        private int patientId;
        private int providerId;
        private int billingId;
        private LocalDateTime appointmentDate;
        private String roomNumber;
        private String reasonForVisiting;

        //optional variables
        private Integer nurseId;
        private Integer prescriptionId;
        private Integer labOrderId;
        private LocalTime startTime;
        private LocalTime endTime;

        //required constructor only
        public Builder(int id, int patientId, int providerId, int billingId, LocalDateTime appointmentDate, String roomNumber, String purpose) {
            this.id = id;
            this.patientId = patientId;
            this.providerId = providerId;
            this.billingId = billingId;
            this.appointmentDate = appointmentDate;
            this.roomNumber = roomNumber;
            this.reasonForVisiting = purpose;
        }

        //method checks for optional variables
        public Builder nurseId(Integer nurseId) {
            this.nurseId = nurseId;
            return this;
        }
        public Builder prescriptionId(Integer prescriptionId) {
            this.prescriptionId = prescriptionId;
            return this;
        }
        public Builder labOrderId(Integer labOrderId) {
            this.labOrderId = labOrderId;
            return this;
        }
        public Builder startTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }
        public Builder endTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        //Combine the required and the provided optional variables
        public Appointment build(){return new Appointment(this);}
    }
}
