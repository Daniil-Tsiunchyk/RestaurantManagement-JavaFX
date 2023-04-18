package com.example.restaurantmanagement.Entities;

import java.sql.Timestamp;
import java.util.List;

public class Order {
    int id;
    String information;
    Double total_cost;
    Timestamp start_time;
    Timestamp end_time;
    int table_id;
    String status;
    List<OrderedDish> orderedDishes;

    public Order(int id, String information, Double total_cost, int table_id, Timestamp start_time, Timestamp end_time, String status) {
        this.id = id;
        this.information = information;
        this.total_cost = total_cost;
        this.start_time = start_time;
        this.end_time = end_time;
        this.table_id = table_id;
        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public List<OrderedDish> getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(List<OrderedDish> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(Double total_cost) {
        this.total_cost = total_cost;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }
}
