/***************************************************************************************
 * Insurance.java
 *
 *      This is a blueprint for storing information related to Insurances and acts as
 *      a model to transfer information between the backend and the frontend.
 *
 *      Contains identifying variables of Insurances and is used by the service layer
 *      for retrieval and updates.
 *      - "id": is a unique identifier that differentiates between Insurances. It is
 *          also the primary way of accessing specific Insurance records. This is
 *          immutable and leads with an "IN". The number combination is created by the
 *          auto-increment feature in MySQL.
 *      - "insurnace_name": the name of the insurance company.
 *      - "phone": the insurance's phone number.
 *      - "email": the insurance's main email.
 *      - "address": the insurance's address.
 *
 * @author Matthew Kiyono
 * @version 1.1
 * @since 10/20/2026
 *******************************************************************************************/

package com.appointmentProject.backend.model;

public class Insurance{

    //variables
    private String id;
    private String insurance_name;
    private int phone;
    private String email;
    private String address;

    //constructor
    public Insurance(String id, String insurance_name, int phone, String email, String address) {
        this.id = id;
        this.insurance_name = insurance_name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    //getter methods
    public String getId() {return id;}
    public String getInsurance_name() {return insurance_name;}
    public int getPhone() {return phone;}
    public String getEmail() {return email;}
    public String getAddress() {return address;}

    //setter methods
    public void setId(String id) {this.id = id;}
    public void setAddress(String address) {this.address = address;}
    public void setInsurance_name(String insurance_name) {this.insurance_name = insurance_name;}
    public void setPhone(int phone) {this.phone = phone;}
    public void setEmail(String email) {this.email = email;}


    //toString method
    public String toString(){
        return "Insurance:\n" +
                "\nIN id: " + id +
                "\ninsurance_name: " + insurance_name +
                "\nphone: " + phone +
                "\nemail: " + email +
                "\naddress: " + address + "\n";
    }

}