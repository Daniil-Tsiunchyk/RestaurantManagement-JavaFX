package com.example.restaurantmanagement.Entities;

import java.math.BigDecimal;

public class Dish {
    int id;
    int recipe_id;
    String name;
    BigDecimal cost;
    String type;

    public Dish(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Dish(int id, int recipe_id, String name, BigDecimal cost, String type) {
        this.id = id;
        this.recipe_id = recipe_id;
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    public Dish(int id, String name, BigDecimal cost, String type) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    public Dish() {

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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "iddish=" + id +
                ", recipe_id=" + recipe_id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", type='" + type + '\'' +
                '}';
    }
}
