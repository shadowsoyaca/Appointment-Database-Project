/***************************************************************************************
 * Manufacturer.java
 *
 *      This is a blueprint for storing information related to Manufacturers and acts as
 *      a model to transfer information between the backend and the frontend.
 *
 *      Contains identifying variables of Manufacturers and is used by the service layer
 *      for retrieval and updates.
 *      - "id": is a unique identifier that differentiates between Manufacturers. It is
 *          also the primary way of accessing specific Manufacturer records. This is
 *          immutable and leads with an "MN". The number combination is created by the
 *          auto-increment feature in MySQL.
 *      - "manufacturer_name": the name of the manufacturer company.
 *      - "phone": the manufacturer's phone number.
 *      - "email": the manufacturer's main email.
 *      - "address": the manufacturer's address.
 *
 * @author Matthew Kiyono
 * @version 1.2
 * @since 10/20/2026
 *******************************************************************************************/

package com.appointmentProject.backend.model;

public class Manufacturer{

    //variables
    private String id;
    private String manufacturer_name;
    private String phone;
    private String email;
    private String address;

    //constructor
    public Manufacturer(String id, String manufacturer_name, String phone, String email, String address) {
        this.id = id;
        this.manufacturer_name = manufacturer_name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    //getter methods
    public String getId() {return id;}
    public String getManufacturer_name() {return manufacturer_name;}
    public String getPhone() {return phone;}
    public String getEmail() {return email;}
    public String getAddress() {return address;}

    //setter methods
    public void setId(String id) {this.id = id;}
    public void setManufacturer_name(String manufacturer_name) {this.manufacturer_name = manufacturer_name;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setEmail(String email) {this.email = email;}
    public void setAddress(String address) {this.address = address;}

    //toString method
    public String toString(){
        return "Manufacturer:\n" +
                "\nMN id: " + id +
                "\n manufacturer name: " + manufacturer_name +
                "\n phone: " + phone +
                "\n email: " + email +
                "\n address: " + address + "\n";
    }




}