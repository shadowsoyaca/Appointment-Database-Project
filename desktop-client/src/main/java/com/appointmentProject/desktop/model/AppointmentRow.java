package com.appointmentProject.desktop.model;

import javafx.beans.property.*;

public class AppointmentRow {

    private final IntegerProperty id;
    private final StringProperty patientName;
    private final StringProperty providerName;
    private final StringProperty appointmentDate;
    private final StringProperty roomNumber;
    private final StringProperty startTime;
    private final StringProperty endTime;

    public AppointmentRow(int id,
                          String patientName,
                          String providerName,
                          String appointmentDate,
                          String roomNumber,
                          String startTime,
                          String endTime) {

        this.id = new SimpleIntegerProperty(id);
        this.patientName = new SimpleStringProperty(patientName);
        this.providerName = new SimpleStringProperty(providerName);
        this.appointmentDate = new SimpleStringProperty(appointmentDate);
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.startTime = new SimpleStringProperty(startTime);
        this.endTime = new SimpleStringProperty(endTime);
    }

    public int getId() { return id.get(); }
    public String getPatientName() { return patientName.get(); }
    public String getProviderName() { return providerName.get(); }
    public String getAppointmentDate() { return appointmentDate.get(); }
    public String getRoomNumber() { return roomNumber.get(); }
    public String getStartTime() { return startTime.get(); }
    public String getEndTime() { return endTime.get(); }

    public IntegerProperty idProperty() { return id; }
    public StringProperty patientNameProperty() { return patientName; }
    public StringProperty providerNameProperty() { return providerName; }
    public StringProperty appointmentDateProperty() { return appointmentDate; }
    public StringProperty roomNumberProperty() { return roomNumber; }
    public StringProperty startTimeProperty() { return startTime; }
    public StringProperty endTimeProperty() { return endTime; }
}
