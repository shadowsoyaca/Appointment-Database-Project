 /******************************************************************************
 * EmergencyContact.java
 *
 *     Represents an emergency contact entity from the database in object
 *     format for transferring from the database to the frontend.
 *
 *     Contains the identifying variables of the emergency contact and is
 *     used by the service layer for retrieval and updates.
 *     - "id": is a unique identifier that differentiates between contacts. It
 *              also is the primary way to get access to the emergency contact's
 *              record. This is immutable and leads with an "EC". This is
 *              created by MySQL's auto-increment feature.
 *     - "first_name", "last_name", "phone", and "email" are originating from Person
 *     - "address": (optional) the contact's address
 *      - "email" is an optional field for this subclass
 *
 *     Design Notes: Builder pattern is utilized in this model to handle optional
 *          variables.
 *          - Imported NullString checker for displying the toString as a "N/A"
 *
 * @author Matthew Kiyono
 * @version 1.1
 * @since 10/16/2025
 ********************************************************************************/

package com.appointmentProject.backend.model;

import com.appointmentProject.backend.abstractmodel.Person;
import com.appointmentProject.backend.util.NullString;


public class EmergencyContact extends Person {

    //required variables
    private String id;

    //optional variables (email is also optional)
    private String address;

    //private constructor for builder
    private EmergencyContact(Builder builder) {
        super(builder.first_name, builder.last_name, builder.phone, builder.email);
        this.id = builder.id;
        this.address = builder.address;
    }

    //getter methods
    public String getId(){return id;}
    public String getAddress(){return address;}

    //setter methods
    public void setId(String id){this.id = id;}
    public void setAddress(String address){this.address = address;}

    //toString
    @Override
    public String toString(){
        return "Emergency Contact:\n\n" +
                "EC id: " + this.id + "\n" +
                super.toString() +
                "address: " + NullString.check(this.address) + "\n";
    }

    public static class Builder{
        //required
        private String id;
        private String first_name;
        private String last_name;
        private int  phone;
        //optional
        private String email;
        private String address;

        //this constructor only utilizes the required variables
        public Builder(String id, String first_name, String last_name, int phone){
            this.id = id;
            this.first_name = first_name;
            this.last_name = last_name;
            this.phone = phone;
        }

        //optional variables
        public Builder email(String email){this.email = email; return this;}
        public Builder address(String address){this.address = address; return this;}

        //putting provided variables together before sending it to private constructor
        public EmergencyContact build(){
            return new EmergencyContact(this);
        }
    }

}
