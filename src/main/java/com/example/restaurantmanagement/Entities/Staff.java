package com.example.restaurantmanagement.Entities;

import java.sql.Date;

public class Staff {
    int idstaff;
    String name;
    String login;
    String password;
    String role;
    Date apparatus_employed;
    Date dismissal_from_work;

    public Staff(int idstaff, String name, String login, String password, String role, Date apparatus_employed, Date dismissal_from_work) {
        this.idstaff = idstaff;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.apparatus_employed = apparatus_employed;
        this.dismissal_from_work = dismissal_from_work;
    }

    public int getIdstaff() {
        return idstaff;
    }

    public void setIdstaff(int idstaff) {
        this.idstaff = idstaff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getApparatus_employed() {
        return apparatus_employed;
    }

    public void setApparatus_employed(Date apparatus_employed) {
        this.apparatus_employed = apparatus_employed;
    }

    public Date getDismissal_from_work() {
        return dismissal_from_work;
    }

    public void setDismissal_from_work(Date dismissal_from_work) {
        this.dismissal_from_work = dismissal_from_work;
    }
}
