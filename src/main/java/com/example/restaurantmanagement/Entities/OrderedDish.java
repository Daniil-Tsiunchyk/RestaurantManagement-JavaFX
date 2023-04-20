package com.example.restaurantmanagement.Entities;

public class OrderedDish {
    int idordered_dish;
    int orderId;
    int dishId;
    int quantity;
    String dish_name;
    String dish_type;
    String status;

    public OrderedDish(int idordered_dish, String dish_name, String dish_type, String status, int quantity) {
        this.idordered_dish = idordered_dish;
        this.dish_name = dish_name;
        this.dish_type = dish_type;
        this.status = status;
        this.quantity = quantity;

    }


    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getDish_type() {
        return dish_type;
    }

    public void setDish_type(String dish_type) {
        this.dish_type = dish_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdordered_dish() {
        return idordered_dish;
    }

    public void setIdordered_dish(int idordered_dish) {
        this.idordered_dish = idordered_dish;
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
