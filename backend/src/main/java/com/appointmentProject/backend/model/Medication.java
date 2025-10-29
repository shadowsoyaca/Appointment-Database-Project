
/******************************************************************************
 * Medication.java
 *
 *     Represents a Medication entity from the database in object
 *     format for transferring from the database to the frontend.
 *
 *     Contains the identifying variables of Medications and is used by the
 *     service layer for retrieval and updates.
 *     - "id": is a unique identifier that differentiates between Medications. It
 *              also is the primary way to get access to the Medication's
 *              record. This is immutable and leads with an "MD". This is
 *              created by MySQL's auto-increment feature.
 *     - "medication_name": the name of the given medication.
 *     - "manufacturer_id": the id number of the manufacturer, (starts with "MN")
 *     - "strength": the dosage amount of the medication.
 *     - "type_of_med": the form of the medication (tablet, liquid, pill, etc.)
 *     - "consumption": how the medication is consumed by the patient (orally,
 *          nasally, injection, etc.)
 *
 *
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 10/29/2025
 ********************************************************************************/
package com.appointmentProject.backend.model;





public class Medication {

    //variables
        private String id;
        private String medication_name;
        private String manufacturer_id;
        private double strength;
        private String type_of_med;
        private String consumption;

    //constructor
    public Medication(String id, String name, String mn_id, double str, String type, String con){
        this.id = id;
        this.medication_name = name;
        this.manufacturer_id = mn_id;
        this.strength = str;
        this.type_of_med = type;
        this.consumption = con;
    }

    //getter
    public String getId() {return id;}
    public String getMedName() {return medication_name;}
    public String getMNID() {return manufacturer_id;}
    public double getStrength() {return strength;}
    public String getType() {return type_of_med;}
    public String getConsumption() {return consumption;}

    //toString
    @Override
    public String toString() {
        return "Medication:" +
                "\nMed ID: " + this.id +
                "\nMed Name: " + this.medication_name +
                "\nManufacturer id: " +  this.manufacturer_id +
                "\nStrength: " +  this.strength +
                "\nType of Medication: " +  this.type_of_med +
                "\nConsumption Method: " +   this.consumption +
                "\n";
    }
}
