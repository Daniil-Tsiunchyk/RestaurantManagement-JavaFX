package com.example.restaurantmanagement.Entities;

public class OrderedDish extends Dish {

    int idOrderedDish;

    int orderId;
    int dishId;
    int tableId;
    String status;


    public OrderedDish(int idOrderedDish, String name, String type, String status) {
        super(name, type);
        this.idOrderedDish = idOrderedDish;
        this.status = status;
    }

    public OrderedDish(int idOrderedDish, String name, String type, String status, int tableId) {
        super(name, type);
        this.idOrderedDish = idOrderedDish;
        this.status = status;
        this.tableId = tableId;

    }

    public int getIdOrderedDish() {
        return idOrderedDish;
    }

    public void setIdOrderedDish(int idOrderedDish) {
        this.idOrderedDish = idOrderedDish;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }
}
