package com.example.restaurantmanagement.Entities;

public class Recipe {
    int id;
    String information;
    String instruction;
    String photo_url;

    public Recipe(int id, String information, String instruction, String photo_url) {
        this.id = id;
        this.information = information;
        this.instruction = instruction;
        this.photo_url = photo_url;
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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
