package com.appointmentProject.backend.model;

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
 *          immutable and leads with an "PH". The number combination is created by the
 *          auto-increment feature in MySQL.
 *      - "pharmacy_name": the name of the pharmacy company.
 *      - "phone": the pharmacy's phone number.
 *      - "email": the pharmacy's main email.
 *      - "address": the pharmacy's address.
 *      - "opening_time": the time the pharmacy opens to the public.
 *      - "closing_time": the time the pharmacy closes to the public.
 *
 * @author Matthew Kiyono
 * @version 1.2
 * @since 10/29/2026
 *******************************************************************************************/

public class Pharmacy {

    //variables
    private String id;
    private String pharmacy_name;
    private String phone;
    private String email;
    private String address;
    private LocalTime  start_time;
    private LocalTime end_time;

    //constructor
    public Pharmacy(String id, String name, String phone, String email, String address, LocalTime start_time, LocalTime end_time){
        this.id = id;
        this.pharmacy_name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    //getters
    public String getId(){return id;}
    public String getName(){return pharmacy_name;}
    public String getPhone(){return phone;}
    public String getEmail(){return email;}
    public String getAddress(){return address;}
    public LocalTime getStartTime(){return start_time;}
    public LocalTime getEndTime(){return end_time;}

    //setters
    public void setId(String id){this.id = id;}
    public void setName(String name){this.pharmacy_name = name;}
    public void setPhone(String phone){this.phone = phone;}
    public void setEmail(String email){this.email = email;}
    public void setAddress(String address){this.address = address;}
    public void setStartTime(LocalTime  start_time){this.start_time = start_time;}
    public void setEndTime(LocalTime  end_time){this.end_time = end_time;}

    //toString
    @Override
    public String toString(){
        return "Pharmacy:" +
                "\nPH id: " + this.id +
                "\nName: " + this.pharmacy_name +
                "\nPhone: " + this.phone +
                "\nEmail: " + this.email +
                "\nAddress: " + this.address +
                "\nOpens: " + this.start_time +
                "\nEnds: " + this.end_time +
                "\n";
    }


}
