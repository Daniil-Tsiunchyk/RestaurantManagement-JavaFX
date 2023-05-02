package com.example.restaurantmanagement.Entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class WorkHours {
    private final IntegerProperty id;
    private final IntegerProperty staffId;
    private final ObjectProperty<LocalDate> date;
    private int hours;

    public WorkHours(int id, int staffId, LocalDate date) {
        this.id = new SimpleIntegerProperty(id);
        this.staffId = new SimpleIntegerProperty(staffId);
        this.date = new SimpleObjectProperty<>(date);
    }

    public WorkHours(int id, int staffId, LocalDate date, int hours) {
        this.id = new SimpleIntegerProperty(id);
        this.staffId = new SimpleIntegerProperty(staffId);
        this.date = new SimpleObjectProperty<>(date);
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getStaffId() {
        return staffId.get();
    }

    public void setStaffId(int staffId) {
        this.staffId.set(staffId);
    }

    public IntegerProperty staffIdProperty() {
        return staffId;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
}