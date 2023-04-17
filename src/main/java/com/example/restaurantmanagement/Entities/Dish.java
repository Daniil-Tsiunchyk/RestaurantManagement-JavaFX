package com.example.restaurantmanagement.Entities;

public class Dish {
    int id;
    int recipe_id;
    String name;
    Double cost;
    String type;

    public Dish(int id, int recipe_id, String name, Double cost, String type) {
        this.id = id;
        this.recipe_id = recipe_id;
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
