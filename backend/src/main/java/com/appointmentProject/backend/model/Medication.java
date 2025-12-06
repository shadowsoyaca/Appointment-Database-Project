
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
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "medication")
@DynamicUpdate
public class Medication {

    //variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @NotNull
    @Column(name = "medicationName", nullable = false)
    private String medicationName;

    @NotNull
    @Column(name = "manufacturerId", nullable = false)
    private int manufacturerId;

    @NotNull
    @Column(name = "strength", nullable = false)
    private double strength;

    @NotNull
    @Column(name = "typeOfMed", nullable = false)
    private String typeOfMed;

    @NotNull
    @Column(name = "consumption", nullable = false)
    private String consumption;

    //Test Constructor Only!
    protected Medication() {}

    //constructor
    public Medication(int id, String name, int mnId, double str, String type, String con){
        this.id = id;
        this.medicationName = name;
        this.manufacturerId = mnId;
        this.strength = str;
        this.typeOfMed = type;
        this.consumption = con;
    }

    //getter
    public int getId() {return id;}
    public String getMedName() {return medicationName;}
    public int getManufacturerId() {return manufacturerId;}
    public double getStrength() {return strength;}
    public String getType() {return typeOfMed;}
    public String getConsumption() {return consumption;}

    //setter method
    public void setId(int id) {this.id = id;}
    public void setMedName(String medicationName) {this.medicationName = medicationName;}
    public void setStrength(double strength) {this.strength = strength;}
    public void setType(String typeOfMed) {this.typeOfMed = typeOfMed;}
    public void setManufacturerId(int manufacturerId) {this.manufacturerId = manufacturerId;}
    public void setConsumption(String consumption) {this.consumption = consumption;}

    //toString
    @Override
    public String toString() {
        return "Medication:" +
                "\nMed ID: " + this.id +
                "\nMed Name: " + this.medicationName +
                "\nManufacturer id: " +  this.manufacturerId +
                "\nStrength: " +  this.strength +
                "\nType of Medication: " +  this.typeOfMed +
                "\nConsumption Method: " +   this.consumption +
                "\n";
    }
}
