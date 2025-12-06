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
 *          immutable. The number combination is created by the
 *          auto-increment feature in MySQL.
 *      - "insurnace_name": the name of the insurance company.
 *      - "phone": the insurance's phone number.
 *      - "email": the insurance's main email.
 *      - "address": the insurance's address.
 *
 * @author Matthew Kiyono
 * @version 1.3
 * @since 10/20/2026
 *******************************************************************************************/

package com.appointmentProject.backend.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "insurance")
@DynamicUpdate
public class Insurance{

    //variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @NotNull
    @Column(name = "insuranceName", nullable = false)
    private String insuranceName;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    //Used for testing purposes only.
    protected Insurance() {}

    //constructor
    public Insurance(int id, String insuranceName, String phone, String email, String address) {
        this.id = id;
        this.insuranceName = insuranceName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    //getter methods
    public int getId() {return id;}
    public String getInsuranceName() {return insuranceName;}
    public String getPhone() {return phone;}
    public String getEmail() {return email;}
    public String getAddress() {return address;}

    //setter methods
    public void setId(int id) {this.id = id;}
    public void setAddress(String address) {this.address = address;}
    public void setInsuranceName(String insuranceName) {this.insuranceName = insuranceName;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setEmail(String email) {this.email = email;}


    //toString method
    public String toString(){
        return "Insurance:\n" +
                "\nIN id: " + id +
                "\ninsuranceName: " + insuranceName +
                "\nphone: " + phone +
                "\nemail: " + email +
                "\naddress: " + address + "\n";
    }

}