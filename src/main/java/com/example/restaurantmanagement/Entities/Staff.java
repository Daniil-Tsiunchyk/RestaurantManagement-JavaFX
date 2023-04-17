package com.example.restaurantmanagement.Entities;

import java.sql.Date;

public class Staff {
    int id;
    String login;
    String password;
    String name;
    java.sql.Date apparatus_employed;
    java.sql.Date dismissal_from_work;

    public Staff(int id, String login, String password, String name, Date apparatus_employed, Date dismissal_from_work) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.apparatus_employed = apparatus_employed;
        this.dismissal_from_work = dismissal_from_work;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
