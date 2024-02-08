package com.example.sqlitepractice;

public class Car {

    // Instance variables
    private int id;
    private String model;
    private String color;
    private double distancePerLitre;

    public Car( String model, String color, double distancePerLitre) {
        this.model = model;
        this.color = color;
        this.distancePerLitre = distancePerLitre;
    }

    // Constructors
    public Car(int id, String model, String color, double distancePerLitre) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.distancePerLitre = distancePerLitre;
    }

    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getDistancePerLitre() {
        return distancePerLitre;
    }

    public void setDistancePerLitre(double distancePerLitre) {
        this.distancePerLitre = distancePerLitre;
    }

    // Other methods can be added based on the behavior of a Car


}