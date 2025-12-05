package com.appointmentProject.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

/***************************************************************************************
 * Availability.java
 *
 *      This is a blueprint for storing information related to Availability and acts as
 *      a model to transfer information between the backend and the frontend.
 *
 *      Contains identifying variables of Availability and is used by the service layer
 *      for retrieval and updates.
 *      - "id": auto-increment primary key for database operations
 *      - "staffId": identifies the staff member
 *      - "staffType": specifies the type of staff (Provider or Nurse)
 *      - "dayOfWeek": specifies the day of week
 *      - "startTime": the start of the availability window
 *      - "endTime": the end of the availability window
 *
 * @version 1.3
 * @since 10/30/2025
 * @author Jack Mitchell
 *******************************************************************************************/
@Entity
@Table(
        name = "availability",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = { "staffId", "staffType", "dayOfWeek" })
        }
)
public class Availability {

    // PRIMARY KEY (added for JPA compatibility)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    // staff information
    @NotNull
    @Column(name = "staffId", nullable = false)
    private int staffId;

    @NotNull
    @Column(name = "staffType", nullable = false)
    private String staffType;

    @NotNull
    @Column(name = "dayOfWeek", nullable = false)
    private String dayOfWeek;

    @NotNull
    @Column(name = "startTime", nullable = false, columnDefinition = "TIME(0)")
    private LocalTime startTime;

    @NotNull
    @Column(name = "endTime", nullable = false, columnDefinition = "TIME(0)")
    private LocalTime endTime;

    // Test Constructor Only
    protected Availability() {}

    // Full constructor
    public Availability(int staffId, String staffType, String dayOfWeek, LocalTime startTime, LocalTime endTime) {

        if (!staffType.equals("Provider") && !staffType.equals("Nurse")) {
            throw new IllegalArgumentException("staffType must be either 'Provider' or 'Nurse'.");
        }
        if (!(dayOfWeek.equals("Mon") || dayOfWeek.equals("Tue") || dayOfWeek.equals("Wed") ||
              dayOfWeek.equals("Thu") || dayOfWeek.equals("Fri") || dayOfWeek.equals("Sat") ||
              dayOfWeek.equals("Sun"))) {
            throw new IllegalArgumentException("dayOfWeek must be Mon–Sun.");
        }
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("startTime must be before endTime.");
        }

        this.staffId = staffId;
        this.staffType = staffType;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // GETTERS
    public int getId() { return id; }
    public int getStaffId() { return staffId; }
    public String getStaffType() { return staffType; }
    public String getDayOfWeek() { return dayOfWeek; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }

    // SETTERS
    public void setStaffId(int staffId) { this.staffId = staffId; }
    public void setStaffType(String staffType) { this.staffType = staffType; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    // toString
    public String toString() {
        return "Availability:\n" +
                "\nID: " + id +
                "\nStaff ID: " + staffId +
                "\nStaff Type: " + staffType +
                "\nDay of Week: " + dayOfWeek +
                "\nStart Time: " + startTime +
                "\nEnd Time: " + endTime + "\n";
    }
}
