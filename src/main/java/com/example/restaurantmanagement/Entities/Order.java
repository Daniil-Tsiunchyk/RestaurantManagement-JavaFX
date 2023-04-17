package com.example.restaurantmanagement.Entities;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    int id;
    String information;
    Double total_cost;
    LocalDateTime timestamp;
    int table_id;
    List<OrderedDish> orderedDishes;

    public Order(int id, String information, Double total_cost, LocalDateTime timestamp, int table_id, List<OrderedDish> orderedDishes) {
        this.id = id;
        this.information = information;
        this.total_cost = total_cost;
        this.timestamp = timestamp;
        this.table_id = table_id;
        this.orderedDishes = orderedDishes;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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
