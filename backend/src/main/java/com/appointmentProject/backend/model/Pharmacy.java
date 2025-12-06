package com.appointmentProject.backend.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalTime;

/***************************************************************************************
 * Pharmacy.java
 *
 *      This is a blueprint for storing information related to Pharmacies and acts as
 *      a model to transfer information between the backend and the frontend.
 *
 *      Contains identifying variables of Pharmacies and is used by the service layer
 *      for retrieval and updates.
 *      - "id": is a unique identifier that differentiates between Pharmacies. It is
 *          also the primary way of accessing specific Pharmacy records. This is
 *          immutable. The number combination is created by the
 *          auto-increment feature in MySQL.
 *      - "pharmacy_name": the name of the pharmacy company.
 *      - "phone": the pharmacy's phone number.
 *      - "email": the pharmacy's main email.
 *      - "address": the pharmacy's address.
 *      - "opening_time": the time the pharmacy opens to the public.
 *      - "closing_time": the time the pharmacy closes to the public.
 *
 * @author Matthew Kiyono
 * @version 1.3
 * @since 10/29/2026
 *******************************************************************************************/
@Entity
@Table(name = "pharmacy")
@DynamicUpdate
public class Pharmacy {

    //variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @NotNull
    @Column(name = "pharmacyName", nullable = false)
    private String pharmacyName;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "openingTime", nullable = false, columnDefinition = "TIME(0)")
    private LocalTime  startTime;

    @NotNull
    @Column(name = "closingTime",  nullable = false, columnDefinition = "TIME(0)")
    private LocalTime endTime;

    //Test COnstructor only!
    protected Pharmacy() {}

    //constructor
    public Pharmacy(int id, String name, String phone, String email, String address, LocalTime startTime, LocalTime endTime){
        this.id = id;
        this.pharmacyName = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //getters
    public int getId(){return id;}
    public String getName(){return pharmacyName;}
    public String getPhone(){return phone;}
    public String getEmail(){return email;}
    public String getAddress(){return address;}
    public LocalTime getStartTime(){return startTime;}
    public LocalTime getEndTime(){return endTime;}

    //setters
    public void setId(int id){this.id = id;}
    public void setName(String name){this.pharmacyName = name;}
    public void setPhone(String phone){this.phone = phone;}
    public void setEmail(String email){this.email = email;}
    public void setAddress(String address){this.address = address;}
    public void setStartTime(LocalTime  startTime){this.startTime = startTime;}
    public void setEndTime(LocalTime  endTime){this.endTime = endTime;}

    //toString
    @Override
    public String toString(){
        return "Pharmacy:" +
                "\nPharmacy id: " + this.id +
                "\nName: " + this.pharmacyName +
                "\nPhone: " + this.phone +
                "\nEmail: " + this.email +
                "\nAddress: " + this.address +
                "\nOpens: " + this.startTime +
                "\nCloses: " + this.endTime +
                "\n";
    }


}
