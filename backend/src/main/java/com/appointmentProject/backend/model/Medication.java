
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
 *              record. This is immutable. This is created by MySQL's auto-increment feature.
 *     - "medication_name": the name of the given medication.
 *     - "manufacturer_id": the id number of the manufacturer
 *     - "strength": the dosage amount of the medication.
 *     - "type_of_med": the form of the medication (tablet, liquid, pill, etc.)
 *     - "consumption": how the medication is consumed by the patient (orally,
 *          nasally, injection, etc.)
 *
 * @author Matthew Kiyono
 * @version 1.2
 * @since 10/29/2025
 ********************************************************************************/
package com.appointmentProject.backend.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "medication")
public class Medication {

    //variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @NotNull
    @Column(name = "medication_name", nullable = false)
    private String medication_name;

    @NotNull
    @Column(name = "manufacturer_id", nullable = false)
    private int manufacturer_id;

    @NotNull
    @Column(name = "strength", nullable = false)
    private double strength;

    @NotNull
    @Column(name = "type_of_med", nullable = false)
    private String type_of_med;

    @NotNull
    @Column(name = "consumption", nullable = false)
    private String consumption;

    //constructor
    public Medication(int id, String name, int mn_id, double str, String type, String con){
        this.id = id;
        this.medication_name = name;
        this.manufacturer_id = mn_id;
        this.strength = str;
        this.type_of_med = type;
        this.consumption = con;
    }

    //getter
    public int getId() {return id;}
    public String getMedName() {return medication_name;}
    public int getManufacturer_id() {return manufacturer_id;}
    public double getStrength() {return strength;}
    public String getType() {return type_of_med;}
    public String getConsumption() {return consumption;}

    //setter method
    public void setId(int id) {this.id = id;}
    public void setMedName(String medication_name) {this.medication_name = medication_name;}
    public void setStrength(double strength) {this.strength = strength;}
    public void setType(String type_of_med) {this.type_of_med = type_of_med;}
    public void setManufacturer_id(int manufacturer_id) {this.manufacturer_id = manufacturer_id;}
    public void setConsumption(String consumption) {this.consumption = consumption;}

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
