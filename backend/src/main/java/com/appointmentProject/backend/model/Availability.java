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
    @Column(name = "staffId", nullable = false, unique = true)
    private int staffId;

    @NotNull
    @Column(name = "staffType", nullable = false)
    private String staffType;

    @NotNull
    @Column(name = "dayOfWeek", nullable = false)
    private String dayOfWeek;

    @NotNull
    @Column(name= "startTime", nullable = false, columnDefinition = "TIME(0)")
    private LocalTime startTime;

    @NotNull
    @Column(name = "endTime",  nullable = false, columnDefinition = "TIME(0)")
    private LocalTime endTime;

    //Test Constructor Only!
    protected Availability() {}

    // constructor
    public Availability(int staffId, String staffType, String dayOfWeek, LocalTime startTime, LocalTime endTime) {
        //input validation
        if (!(staffType.equals("Provider") || staffType.equals("Nurse"))) {
            throw new IllegalArgumentException("staffType must be either 'Provider' or 'Nurse'.");
        }
        if (!(dayOfWeek.equals("Mon") || dayOfWeek.equals("Tue") || dayOfWeek.equals("Wed") || dayOfWeek.equals("Thu") || dayOfWeek.equals("Fri") || dayOfWeek.equals("Sat") || dayOfWeek.equals("Sun"))) {
            throw new IllegalArgumentException("dayOfWeek must be either 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', or 'Sun'.");
        }
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("startTime must be before endTime");
        }

        this.staffId = staffId;
        this.staffType = staffType;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // getter methods
    public int getStaffId() {return staffId;}
    public String getStaffType() {return staffType;}
    public String getDayOfWeek() {return dayOfWeek;}
    public LocalTime getStartTime() {return startTime;}
    public LocalTime getEndTime() {return endTime;}

    //setter methods
    public void setStaffId(int staffId) {this.staffId = staffId;}
    public void setStaffType(String staffType) {this.staffType = staffType;}
    public void setDayOfWeek(String dayOfWeek) {this.dayOfWeek = dayOfWeek;}
    public void setStartTime(LocalTime startTime) {this.startTime = startTime;}
    public void setEndTime(LocalTime  endTime) {this.endTime = endTime;}

    // toString method
    public String toString() {
        return "Availability:\n" +
                "\nAV id: " + staffId +
                "\nstaffType: " + staffType +
                "\nday_of_week: " + dayOfWeek +
                "\nstart_time: " + startTime +
                "\nend_time: " + endTime + "\n";
    }


}
