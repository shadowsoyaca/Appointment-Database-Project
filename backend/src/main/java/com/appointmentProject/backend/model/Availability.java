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
 *          immutable and leads with an "AV". The number combination is created by the
 *          auto-increment feature in MySQL.
 *      - "staff_type": specifies the type of staff member. Either "Provider" or "Nurse".
 *      - "day_of_week": specifies the day of week.
 *      - "start_time": the start time of the Availability in military time.
 *      - "end_time": the end time of the Availability in military time.
 *
 * @author Jack Mitchell
 * @version 1.1
 * @since 10/30/2025
 *******************************************************************************************/

package com.appointmentProject.backend.model;
import java.time.LocalTime;
import java.lang.IllegalArgumentException;

public class Availability {

    // variables
    private String staff_id;
    private String staff_type;
    private String day_of_week;
    private LocalTime start_time;
    private LocalTime end_time;

    // constructor
    public Availability(String staff_id, String staff_type, String day_of_week, LocalTime start_time, LocalTime end_time) {
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
    public String getStaff_id() {return staff_id;}
    public String getStaff_type() {return staff_type;}
    public String getDay_of_week() {return day_of_week;}
    public LocalTime getStart_time() {return start_time;}
    public LocalTime getEnd_time() {return end_time;}

    //setter methods
    public void setStaff_id(String staff_id) {this.staff_id = staff_id;}
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
