package com.appointmentProject.backend.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.appointmentProject.backend.util.NullString;

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
 *          immutable and leads with an "PR". The number combination is created by the
 *          auto-increment feature in MySQL.
 *      - "medication_id": the "MD" id for the medication prescribed.
 *      - "pharmacy_id": the "PR" id for the pharmacy the medication was sent to.
 *      - "insurance_id": (nullable) the "IN" id for the insurance that could be covering it.
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
 * @version 1.0
 * @since 10/30/2026
 *******************************************************************************************/
public class Prescription {

    //required
    private String id;
    private String medication_id;
    private String pharmacy_id;
    private int quantity;
    private int frequency;
    private LocalDate start_date;
    private double price;
    private String status;

    //optional
    private String insurance_id;
    private LocalDate end_date;
    private LocalDateTime date_picked_up;

    //private constructor
    private Prescription(Builder builder) {
        this.id = id;
        this.medication_id = medication_id;
        this.pharmacy_id = pharmacy_id;
        this.quantity = quantity;
        this.frequency = frequency;
        this.start_date = start_date;
        this.price = price;
        this.status = status;
        this.insurance_id = insurance_id;
        this.end_date = end_date;
        this.date_picked_up = date_picked_up;
    }

    //getters
    public String getId() {return id;}
    public String getMedID() {return medication_id;}
    public String getPID() {return pharmacy_id;}
    public int getQuantity() {return quantity;}
    public int getFrequency() {return frequency;}
    public LocalDate getStartDate() {return start_date;}
    public double getPrice() {return price;}
    public String getStatus() {return status;}
    public String getINID() {return insurance_id;}
    public LocalDate getEndDate() {return end_date;}

    //toString
    @Override
    public String toString(){
        return "Prescription: " +
               "\nPrescription ID: " + id +
               "\nMedication ID: " + medication_id +
               "\nPharmacy ID: " + pharmacy_id +
               "\nQuantity: " + quantity +
               "\nFrequency: " + frequency +
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
        private String id;
        private String medication_id;
        private String pharmacy_id;
        private int quantity;
        private int frequency;
        private LocalDate start_date;
        private double price;
        private String status;

        //optional
        private String insurance_id;
        private LocalDate end_date;
        private LocalDateTime date_picked_up;

        public Builder(String id, String med_id, String ph_id, int quan, int freq, LocalDate start, double price, String status){
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
        public Builder insurance_id(String insurance_id){
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
