package com.appointmentProject.backend.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.appointmentProject.backend.util.NullString;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

/***************************************************************************************
 * Prescription.java
 *
 *      This is a blueprint for storing information related to Prescriptions and acts as
 *      a model to transfer information between the backend and the frontend.
 *
 *      Contains identifying variables of Prescriptions and is used by the service layer
 *      for retrieval and updates.
 *      - "id": is a unique identifier that differentiates between Prescriptions. It is
 *          also the primary way of accessing specific Prescription records. This is
 *          immutable. The number combination is created by the
 *          auto-increment feature in MySQL.
 *      - "medication_id": the id for the medication prescribed.
 *      - "pharmacy_id": the id for the pharmacy the medication was sent to.
 *      - "insurance_id": (nullable) the id for the insurance that could be covering it.
 *          - If the insurance is NULL, it is assumed that either the patient does not
 *          have any insurance OR the insurance does not cover the prescription.
 *      - "quantity": the amount of pills/liquid/etc. is given to the patient.
 *      - "frequency": the number of times a day they are required to take the medication.
 *      - "start_date": the date that they are prescribed the medication
 *      - "end_date": (nullable) the date that the individual stops taking the medication.
 *      - "cost": the cost of the medication
 *      - "status": the stage from which the medication is prepared and handed off to the
 *              patient. The only acceptable statuses are:
 *              - Received: The customer picked up the order.
 *              - Ready for Pickup: The order is ready for pickup.
 *              - Processing: The pharmacy is packaging medication and accessing costs/inventory
 *              - Restocking: The pharmacy is out of stock and waiting for their shipment.
 *      - "date_picked_up": (nullable) the date and time the prescription is picked up.
 *
 * @author Matthew Kiyono
 * @version 1.3
 * @since 10/30/2026
 *******************************************************************************************/
@Entity
@Table(name = "prescription")
public class Prescription {

    //required
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", nullable = false,  unique = true)
    private int id;

    @NotNull
    @Column(name = "medicationId", nullable = false)
    private int medicationId;

    @NotNull
    @Column(name = "pharmacyId",  nullable = false)
    private int pharmacyId;

    @NotNull
    @Column(name = "quantity",  nullable = false)
    private int quantity;

    @NotNull
    @Column(name = "frequency", nullable = false)
    private int frequency;

    @NotNull
    @Column(name = "startDate",  nullable = false, columnDefinition = "DATE(0)")
    private LocalDate startDate;

    @NotNull
    @Column(name = "price", nullable = false)
    private double price;

    @NotNull
    @Column(name = "_status", nullable = false)
    private String status;


    //optional
    @Column(name = "insuranceId")
    private Integer insuranceId;

    @Column(name = "endDate", columnDefinition = "DATE(0)")
    private LocalDate endDate;

    @Column(name = "datePickedUp", columnDefinition = "DATETIME(0)")
    private LocalDateTime datePickedUp;

    //Test Constructor ONly!
    protected Prescription() {}

    //private constructor
    private Prescription(Builder builder) {
        this.id = builder.id;
        this.medicationId = builder.medicationId;
        this.pharmacyId = builder.pharmacyId;
        this.quantity = builder.quantity;
        this.frequency = builder.frequency;
        this.startDate = builder.startDate;
        this.price = builder.price;
        this.status = builder.status;
        this.insuranceId = builder.insuranceId;
        this.endDate = builder.endDate;
        this.datePickedUp = builder.datePickedUp;
    }

    //getters
    public int getId() {return id;}
    public int getMedicationId() {return medicationId;}
    public int getPharmacyId() {return pharmacyId;}
    public int getQuantity() {return quantity;}
    public int getFrequency() {return frequency;}
    public LocalDate getStartDate() {return startDate;}
    public double getPrice() {return price;}
    public String getStatus() {return status;}
    public Integer getInsuranceId() {return insuranceId;}
    public LocalDate getEndDate() {return endDate;}
    public  LocalDateTime getDatePickedUp() {return datePickedUp;}

    //setters
    public void setId(int id) {this.id = id;}
    public void setMedicationId(int medicationId) {this.medicationId = medicationId;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setFrequency(int frequency) {this.frequency = frequency;}
    public void setPharmacyId(int pharmacyId) {this.pharmacyId = pharmacyId;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}
    public void setPrice(double price) {this.price = price;}
    public void setStatus(String status) {this.status = status;}
    public void setInsuranceId(Integer insuranceId) {this.insuranceId = insuranceId;}
    public void setEndDate(LocalDate  endDate) {this.endDate = endDate;}
    public void setDatePickedUp(LocalDateTime pickedUp) {this.datePickedUp = pickedUp;}

    //toString
    @Override
    public String toString(){
        return "Prescription: " +
               "\nPrescription ID: " + id +
               "\nMedication ID: " + medicationId +
               "\nPharmacy ID: " + pharmacyId +
               "\nQuantity: " + quantity +
               "\nFrequency: " + frequency + " daily" +
               "\nPrescribed Date: " + startDate +
               "\nPrice: " + price +
               "\nStatus: " + status +
               "\nInsurance ID: " + NullString.check(insuranceId) +
               "\nEnd Date: " + NullString.check(endDate) +
               "\nDate Picked Up: " + NullString.check(datePickedUp) +
                "\n";
    }

    //Builder Constructor
    public static class Builder {

        //required
        private int id;
        private int medicationId;
        private int pharmacyId;
        private int quantity;
        private int frequency;
        private LocalDate startDate;
        private double price;
        private String status;

        //optional
        private Integer insuranceId;
        private LocalDate endDate;
        private LocalDateTime datePickedUp;

        public Builder(int id, int medId, int phId, int quan, int freq, LocalDate start, double price, String status){
            this.id = id;
            this.medicationId = medId;
            this.pharmacyId = phId;
            this.quantity = quan;
            this.frequency = freq;
            this.startDate = start;
            this.price = price;
            this.status = status;
        }

        //optional additions
        public Builder insuranceId(Integer insuranceId){
            this.insuranceId = insuranceId;
            return this;
        }
        public Builder endDate(LocalDate date){
            this.endDate = date;
            return this;
        }
        public Builder datePickedUp(LocalDateTime date){
            this.datePickedUp = date;
            return this;
        }

        //combining provided variables
        public Prescription build(){return new Prescription(this);}
    }

}
