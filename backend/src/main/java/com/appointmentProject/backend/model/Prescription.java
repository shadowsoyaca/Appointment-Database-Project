package com.appointmentProject.backend.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.appointmentProject.backend.util.NullString;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
public class Prescription {

    //required
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int medication_id;
    private int pharmacy_id;
    private int quantity;
    private int frequency;
    private LocalDate start_date;
    private double price;
    private String status;

    //optional
    private Integer insurance_id;
    private LocalDate end_date;
    private LocalDateTime date_picked_up;

    //private constructor
    private Prescription(Builder builder) {
        this.id = builder.id;
        this.medication_id = builder.medication_id;
        this.pharmacy_id = builder.pharmacy_id;
        this.quantity = builder.quantity;
        this.frequency = builder.frequency;
        this.start_date = builder.start_date;
        this.price = builder.price;
        this.status = builder.status;
        this.insurance_id = builder.insurance_id;
        this.end_date = builder.end_date;
        this.date_picked_up = builder.date_picked_up;
    }

    //getters
    public int getId() {return id;}
    public int getMedication_id() {return medication_id;}
    public int getPharmacy_id() {return pharmacy_id;}
    public int getQuantity() {return quantity;}
    public int getFrequency() {return frequency;}
    public LocalDate getStartDate() {return start_date;}
    public double getPrice() {return price;}
    public String getStatus() {return status;}
    public Integer getInsurance_id() {return insurance_id;}
    public LocalDate getEndDate() {return end_date;}
    public  LocalDateTime getDatePickedUp() {return date_picked_up;}

    //setters
    public void setId(int id) {this.id = id;}
    public void setMedication_id(int medication_id) {this.medication_id = medication_id;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setFrequency(int frequency) {this.frequency = frequency;}
    public void setPharmacy_id(int pharmacy_id) {this.pharmacy_id = pharmacy_id;}
    public void setStartDate(LocalDate start_date) {this.start_date = start_date;}
    public void setPrice(double price) {this.price = price;}
    public void setStatus(String status) {this.status = status;}
    public void setInsurance_id(Integer insurance_id) {this.insurance_id = insurance_id;}
    public void setEndDate(LocalDate  end_date) {this.end_date = end_date;}
    public void setDate_picked_up(LocalDateTime picked_up) {this.date_picked_up = picked_up;}

    //toString
    @Override
    public String toString(){
        return "Prescription: " +
               "\nPrescription ID: " + id +
               "\nMedication ID: " + medication_id +
               "\nPharmacy ID: " + pharmacy_id +
               "\nQuantity: " + quantity +
               "\nFrequency: " + frequency + " daily" +
               "\nPrescribed Date: " + start_date +
               "\nPrice: " + price +
               "\nStatus: " + status +
               "\nInsurance ID: " + NullString.check(insurance_id) +
               "\nEnd Date: " + NullString.check(end_date) +
               "\nDate Picked Up: " + NullString.check(date_picked_up) +
                "\n";
    }

    //Builder Constructor
    public static class Builder {

        //required
        private int id;
        private int medication_id;
        private int pharmacy_id;
        private int quantity;
        private int frequency;
        private LocalDate start_date;
        private double price;
        private String status;

        //optional
        private Integer insurance_id;
        private LocalDate end_date;
        private LocalDateTime date_picked_up;

        public Builder(int id, int med_id, int ph_id, int quan, int freq, LocalDate start, double price, String status){
            this.id = id;
            this.medication_id = med_id;
            this.pharmacy_id = ph_id;
            this.quantity = quan;
            this.frequency = freq;
            this.start_date = start;
            this.price = price;
            this.status = status;
        }

        //optional additions
        public Builder insurance_id(Integer insurance_id){
            this.insurance_id = insurance_id;
            return this;
        }
        public Builder end_date(LocalDate date){
            this.end_date = date;
            return this;
        }
        public Builder date_picked_up(LocalDateTime date){
            this.date_picked_up = date;
            return this;
        }

        //combining provided variables
        public Prescription build(){return new Prescription(this);}
    }

}
