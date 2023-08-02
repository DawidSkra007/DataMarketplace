package com.example.tldr.Model;

import java.beans.JavaBean;
import java.time.LocalDateTime;

@JavaBean
public class Dataset {
    private String title = "";
    private String description;
    private double price_per_point;
    private int available_dataPoints;
    private int minQuantity;
    private int maxQuantity;
    private int id;
    private LocalDateTime timestamp;

    // private Data data;
    public Dataset() {

    }

    public Dataset(String title, String data) {
        this.title = title;
        this.description = data;
    }

    public Dataset(String title, String data, double price) {
        this.title = title;
        this.description = data;
        this.price_per_point = price;
    }
    public Dataset(String title, String description, double price,int minQuantity, int maxQuantity,int id,int available_dataPoints) {
        this.title = title;
        this.description = description;
        this.price_per_point = price;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.id = id;
        this.available_dataPoints = available_dataPoints;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice_per_point() {
        return this.price_per_point;
    }

    public void setPrice_per_point(double newPrice) {
        this.price_per_point = newPrice;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getAvailable_dataPoints() {
        return this.available_dataPoints;
    }

    public void setAvailable_dataPoints(int available_dataPoints) {
        this.available_dataPoints = available_dataPoints;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minValue) {
        this.minQuantity = minValue;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxValue) {
        this.maxQuantity = maxValue;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" +
                " Title: " + getTitle() +
                ", Data: " + getDescription() +
                ", Price: " + getPrice_per_point() +
                ", MinQuantity: " + getMinQuantity() +
                ", MaxQuantity: " + getMaxQuantity() +
                ", Available Datapoints: " + getAvailable_dataPoints() +
                " ]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Dataset){
            Dataset d = (Dataset) object;
            return this.getId() == d.getId();
        }
        return false;
    }
}
