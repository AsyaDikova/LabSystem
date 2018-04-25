package org.softuni.laboratory.patient.models.binding;

import java.util.Date;

public class RegisterPatientBindingModel {
    private String email;

    private boolean isConultation;

    private Date dateOfOccurrence;

    public RegisterPatientBindingModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isConultation() {
        return isConultation;
    }

    public void setConultation(boolean conultation) {
        isConultation = conultation;
    }

    public Date getDateOfOccurrence() {
        return dateOfOccurrence;
    }

    public void setDateOfOccurrence(Date dateOfOccurrence) {
        this.dateOfOccurrence = dateOfOccurrence;
    }
}
