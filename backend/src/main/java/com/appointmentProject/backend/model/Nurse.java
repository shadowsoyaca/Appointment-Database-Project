/*****************************************************************************************
 * Nurse.java
 *
 *      This is the blueprint for storing information related to Nurses and acts as a
 *      model to transfer information between the backend and the frontend.
 *
 *      Contains identifying variables of the Nurse and is used by the service layer for
 *      retrieval and updates.
 *      - "id": is a unique identifier that differentiates between Nurses. It is also the
 *          primary way of accessing specific nurse records. This is immutable.
 *          The number combination is created by the auto-increment feature
 *          in MySQL.
 *      - "first_name", "last_name", "phone", and "email" are inherited from Person.java.
 *      - "address": the Nurse's address.
 *
 *
 * @author Matthew Kiyono
 * @version 1.3
 * @since 10/20/2025
 ****************************************************************************************/

package com.appointmentProject.backend.model;

import com.appointmentProject.backend.abstractmodel.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Nurse extends Person {

    //variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;

    //Constructor
    public Nurse(int id, String first_name, String last_name, String phone, String email, String address) {
        super(first_name, last_name, phone, email);
        this.id = id;
        this.address = address;
    }

    //getter method
    public int getId() {return id;}
    public String getAddress() {return address;}

    //setter method
    public void setId(int id) {this.id = id;}
    public void setAddress(String address) {this.address = address;}

    //toString
    public String toString(){
        return "Nurse:\n" +
                "Nurse id: " + this.id + "\n" +
                super.toString() +
                "\nAddress: " + this.address + "\n";
    }
}