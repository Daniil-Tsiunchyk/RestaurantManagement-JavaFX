package com.example.restaurantmanagement.Entities;

public class Dish {
    int id;
    int recipe_id;
    String name;
    Double cost;
    int type_id; //  реализовать  String type;
    int order_id;

    public Dish(int id, int recipe_id, String name, Double cost, int type_id, int order_id) {
        this.id = id;
        this.recipe_id = recipe_id;
        this.name = name;
        this.cost = cost;
        this.type_id = type_id;
        this.order_id = order_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
