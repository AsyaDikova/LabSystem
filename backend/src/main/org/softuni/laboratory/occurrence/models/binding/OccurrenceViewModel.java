package org.softuni.laboratory.occurrence.models.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class OccurrenceViewModel {

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    @Size(min = 9, max = 16)
    private int hours;

    @NotNull
    private boolean isConsultation;

    @NotNull
    private String analysesName;

    public OccurrenceViewModel() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public boolean isConsultation() {
        return isConsultation;
    }

    public void setConsultation(boolean consultation) {
        isConsultation = consultation;
    }

    public String getAnalysesName() {
        return analysesName;
    }

    public void setAnalysesName(String analysesName) {
        this.analysesName = analysesName;
    }
}
