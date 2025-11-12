package com.appointmentProject.backend.model;

import java.time.LocalDateTime;

public class LabOrder {

    private final int id;            
    private int appointmentId;                 
    private int providerRequesterId;   
    private Integer providerReceiverId;   
    private Integer nurseId;             
    private int patientId;                  
    private LocalDateTime dateOfCompletion;      
    private String testingPurpose;                
    private boolean results;                       

    // Private constructor for Builder
    private LabOrder(Builder builder) {
        this.id = builder.id;
        this.appointmentId = builder.appointmentId;
        this.providerRequesterId = builder.providerRequesterId;
        this.providerReceiverId = builder.providerReceiverId;
        this.nurseId = builder.nurseId;
        this.patientId = builder.patientId;
        this.dateOfCompletion = builder.dateOfCompletion;
        this.testingPurpose = builder.testingPurpose;
        this.results = builder.results;
    }

    // Static inner Builder class
    public static class Builder {
        private final int id;
        private int appointmentId;
        private int providerRequesterId;
        private Integer providerReceiverId;
        private Integer nurseId;
        private int patientId;
        private LocalDateTime dateOfCompletion;
        private String testingPurpose;
        private boolean results;

        public Builder(int id, int appointmentId, int providerRequesterId, int patientId, String testingPurpose) {
            this.id = id;
            this.appointmentId = appointmentId;
            this.providerRequesterId = providerRequesterId;
            this.patientId = patientId;
            this.testingPurpose = testingPurpose;
        }

        public Builder providerReceiverId(Integer providerReceiverId) {
            this.providerReceiverId = providerReceiverId;
            return this;
        }

        public Builder nurseId(Integer nurseId) {
            this.nurseId = nurseId;
            return this;
        }

        public Builder dateOfCompletion(LocalDateTime dateOfCompletion) {
            this.dateOfCompletion = dateOfCompletion;
            return this;
        }

        public Builder results(boolean results) {
            this.results = results;
            return this;
        }

        public LabOrder build() {
            return new LabOrder(this);
        }
    }

    // Getters
    public int getId() { return id; }
    public int getAppointmentId() { return appointmentId; }
    public int getProviderRequesterId() { return providerRequesterId; }
    public Integer getProviderReceiverId() { return providerReceiverId; }
    public Integer getNurseId() { return nurseId; }
    public int getPatientId() { return patientId; }
    public LocalDateTime getDateOfCompletion() { return dateOfCompletion; }
    public String getTestingPurpose() { return testingPurpose; }
    public boolean getResults() { return results; }

    // Setters
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }
    public void setProviderRequesterId(int providerRequesterId) { this.providerRequesterId = providerRequesterId; }
    public void setProviderReceiverId(Integer providerReceiverId) { this.providerReceiverId = providerReceiverId; }
    public void setNurseId(Integer nurseId) { this.nurseId = nurseId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public void setDateOfCompletion(LocalDateTime dateOfCompletion) { this.dateOfCompletion = dateOfCompletion; }
    public void setTestingPurpose(String testingPurpose) { this.testingPurpose = testingPurpose; }
    public void setResults(boolean results) { this.results = results; }

    
    @Override
    public String toString() {
        return "LabOrder{" +
                "id=" + id +
                ", appointmentId=" + appointmentId +
                ", providerRequesterId=" + providerRequesterId +
                ", providerReceiverId=" + providerReceiverId +
                ", nurseId=" + nurseId +
                ", patientId=" + patientId +
                ", dateOfCompletion=" + dateOfCompletion +
                ", testingPurpose='" + testingPurpose + '\'' +
                ", results=" + results +
                '}';
    }
}
