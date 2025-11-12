/***************************************************************************************
 * Availability.java
 *
 *      This is a blueprint for storing information related to Availability and acts as
 *      a model to transfer information between the backend and the frontend.
 *
 *      Contains identifying variables of Availability and is used by the service layer
 *      for retrieval and updates.
 *      - "staff_id": is a unique identifier that differentiates between Availabilities. It is
 *          also the primary way of accessing specific Availability records. This is
 *          immutable. The number combination is created by the auto-increment feature in MySQL.
 *      - "staff_type": specifies the type of staff member. Either "Provider" or "Nurse".
 *      - "day_of_week": specifies the day of week.
 *      - "start_time": the start time of the Availability in military time.
 *      - "end_time": the end time of the Availability in military time.
 *
 * @author Jack Mitchell
 * @version 1.2
 * @since 10/30/2025
 *******************************************************************************************/

package com.appointmentProject.backend.model;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalTime;
import java.lang.IllegalArgumentException;

@Entity
@Table(name = "availability")
public class Availability {

    // variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "staff_id", nullable = false, unique = true)
    private int staff_id;

    @NotNull
    @Column(name = "staff_type", nullable = false)
    private String staff_type;

    @NotNull
    @Column(name = "day_of_week", nullable = false)
    private String day_of_week;

    @NotNull
    @Column(name= "start_time", nullable = false, columnDefinition = "TIME(0)")
    private LocalTime start_time;

    @NotNull
    @Column(name = "end_time",  nullable = false, columnDefinition = "TIME(0)")
    private LocalTime end_time;

    //Test Constructor Only!
    protected Availability() {}

    // constructor
    public Availability(int staff_id, String staff_type, String day_of_week, LocalTime start_time, LocalTime end_time) {
        //input validation
        if (!(staff_type.equals("Provider") || staff_type.equals("Nurse"))) {
            throw new IllegalArgumentException("staff_type must be either 'Provider' or 'Nurse'.");
        }
        if (!(day_of_week.equals("Mon") || day_of_week.equals("Tue") || day_of_week.equals("Wed") || day_of_week.equals("Thu") || day_of_week.equals("Fri") || day_of_week.equals("Sat") || day_of_week.equals("Sun"))) {
            throw new IllegalArgumentException("day_of_week must be either 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', or 'Sun'.");
        }
        if (start_time.isAfter(end_time)) {
            throw new IllegalArgumentException("start_time must be before end_time");
        }

        this.staff_id = staff_id;
        this.staff_type = staff_type;
        this.day_of_week = day_of_week;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    // getter methods
    public int getStaff_id() {return staff_id;}
    public String getStaff_type() {return staff_type;}
    public String getDay_of_week() {return day_of_week;}
    public LocalTime getStart_time() {return start_time;}
    public LocalTime getEnd_time() {return end_time;}

    //setter methods
    public void setStaff_id(int staff_id) {this.staff_id = staff_id;}
    public void setStaff_type(String staff_type) {this.staff_type = staff_type;}
    public void setDay_of_week(String day_of_week) {this.day_of_week = day_of_week;}
    public void setStart_time(LocalTime start_time) {this.start_time = start_time;}
    public void setEnd_time(LocalTime  end_time) {this.end_time = end_time;}

    // toString method
    public String toString() {
        return "Availability:\n" +
                "\nAV id: " + staff_id +
                "\nstaff_type: " + staff_type +
                "\nday_of_week: " + day_of_week +
                "\nstart_time: " + start_time +
                "\nend_time: " + end_time + "\n";
    }


}
