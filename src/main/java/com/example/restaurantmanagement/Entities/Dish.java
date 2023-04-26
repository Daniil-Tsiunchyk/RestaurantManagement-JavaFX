package com.example.restaurantmanagement.Entities;

import java.math.BigDecimal;

public class Dish {
    int iddish;
    int recipe_id;
    String name;
    BigDecimal cost;
    String type;

    public Dish(int iddish, int recipe_id, String name, BigDecimal cost, String type) {
        this.iddish = iddish;
        this.recipe_id = recipe_id;
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    public Dish(int iddish, String name, BigDecimal cost, String type) {
        this.iddish = iddish;
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


    public int getIddish() {
        return iddish;
    }

    public void setIddish(int iddish) {
        this.iddish = iddish;
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
                "iddish=" + iddish +
                ", recipe_id=" + recipe_id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", type='" + type + '\'' +
                '}';
    }
}
