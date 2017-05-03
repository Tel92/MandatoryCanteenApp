package com.example.tomas.mandatorycanteenapp;

import java.io.Serializable;

/**
 * Created by tomas on 07-04-2017.
 */

public class Dish implements Serializable
{

    private double Alcohol;
    private double Carbohydrates;
    private String Description;
    private int Energy;
    private int Fat;
    private int Id;
    private double Price;
    private double Protein;
    private String Title;
    private double Weight;


    public Dish() {}

    public Dish(double alcohol, double carbohydrates, String description, int energy, int fat, int id, double price, double protein, String title, double weight) {
        Alcohol = alcohol;
        Carbohydrates = carbohydrates;
        Description = description;
        Energy = energy;
        Fat = fat;
        Id = id;
        Price = price;
        Protein = protein;
        Title = title;
        Weight = weight;

    }

    public double getAlcohol() {
        return Alcohol;
    }

    public void setAlcohol(double alcohol) {
        Alcohol = alcohol;
    }

    public double getCarbohydrates() {
        return Carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        Carbohydrates = carbohydrates;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getEnergy() {
        return Energy;
    }

    public void setEnergy(int energy) {
        Energy = energy;
    }

    public double getFat() {
        return Fat;
    }

    public void setFat(int fat) {
        Fat = fat;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getProtein() {
        return Protein;
    }

    public void setProtein(double protein) {
        Protein = protein;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }



    @Override
    public String toString() {
        return "Dish{" +
                "Alcohol=" + Alcohol +
                ", Carbohydrates=" + Carbohydrates +
                ", Description='" + Description + '\'' +
                ", Energy=" + Energy +
                ", Fat=" + Fat +
                ", Id=" + Id +
                ", Price=" + Price +
                ", Protein=" + Protein +
                ", Title='" + Title + '\'' +
                ", Weight=" + Weight +

                '}';
    }
}
