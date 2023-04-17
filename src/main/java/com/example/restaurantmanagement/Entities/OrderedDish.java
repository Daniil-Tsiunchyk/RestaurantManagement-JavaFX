package com.example.restaurantmanagement.Entities;

public class OrderedDish {
    int orderId;
    int dishId;
    int quantity;

    public OrderedDish(int orderId, int dishId, int quantity) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
