/*****************************************************************************************
 * Nurse.java
 *
 *      This is the blueprint for storing information related to Nurses and acts as a
 *      model to transfer information between the backend and the frontend.
 *
 *      Contains identifying variables of the Nurse and is used by the service layer for
 *      retrieval and updates.
 *      - "id": is a unique identifier that differentiates between Nurses. It is also the
 *          primary way of accessing specific nurse records. This is immutable and leads
 *          with a "NE". The number combination is created by the auto-increment feature
 *          in MySQL.
 *      - "first_name", "last_name", "phone", and "email" are inherited from Person.java.
 *      - "address": the Nurse's address.
 *
 *      Designer Note: As there are no optional parameters, this is created through a
 *          traditional constructor.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 10/20/2025
 ****************************************************************************************/

package com.appointmentProject.backend.model;

import com.appointmentProject.backend.abstractmodel.Person;

public class Nurse extends Person {

    //variables
    private String id;
    private String address;

    //Constructor
    public Nurse(String id, String first_name, String last_name, int phone, String email, String address) {
        super(first_name, last_name, phone, email);
        this.id = id;
        this.address = address;
    }

    //getter method
    public String getId() {return id;}
    public String getAddress() {return address;}

    //toString
    public String toString(){
        return "Nurse:\n\n" +
                "NE id: " + this.id + "\n" +
                super.toString() +
                "Address: " + this.address + "\n";
    }
}