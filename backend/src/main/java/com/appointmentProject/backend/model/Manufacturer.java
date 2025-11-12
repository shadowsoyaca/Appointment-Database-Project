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
 *          immutable. The number combination is created by the
 *          auto-increment feature in MySQL.
 *      - "manufacturer_name": the name of the manufacturer company.
 *      - "phone": the manufacturer's phone number.
 *      - "email": the manufacturer's main email.
 *      - "address": the manufacturer's address.
 *
 * @author Matthew Kiyono
 * @version 1.3
 * @since 10/20/2026
 *******************************************************************************************/

package com.appointmentProject.backend.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "manufacturer")
public class Manufacturer{

    //variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", nullable = false,  unique = true)
    private int id;

    @NotNull
    @Column(name = "manufacturer_name",  nullable = false)
    private String manufacturer_name;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "email",  nullable = false)
    private String email;

    @NotNull
    @Column(name = "address",  nullable = false)
    private String address;

    //Test Constructor Only!
    protected Manufacturer() {}

    //constructor
    public Manufacturer(int id, String manufacturer_name, String phone, String email, String address) {
        this.id = id;
        this.manufacturer_name = manufacturer_name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    //getter methods
    public int getId() {return id;}
    public String getManufacturer_name() {return manufacturer_name;}
    public String getPhone() {return phone;}
    public String getEmail() {return email;}
    public String getAddress() {return address;}

    //setter methods
    public void setId(int id) {this.id = id;}
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