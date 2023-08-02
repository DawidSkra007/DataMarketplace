package com.example.tldr.Model;

import java.beans.JavaBean;

@JavaBean
public class ProductDataset extends Dataset
{
    //private Dataset dataset;
    private int quantity;
    private double total_price;

    public ProductDataset(Dataset dataset,int quantity){

        super(dataset.getTitle(), dataset.getDescription(), dataset.getPrice_per_point(), dataset.getMinQuantity(), dataset.getMaxQuantity(), dataset.getId(),dataset.getAvailable_dataPoints());
        this.quantity = quantity;
        this.total_price = this.getPrice_per_point()*quantity;
    }

//    public Dataset getDataset() {return dataset;}
//
//    //if dataset is changed, price is updated to reflect this
//    public void setDataset(Dataset dataset) {
//        this.dataset = dataset;
//        setPrice();
//    }

    public int getQuantity() {return quantity;}

    //if quantity is changed, price is updated to reflect this
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
        setPrice();
    }

    public double getTotal_price() {return total_price;}

    public void setPrice() {
        total_price = this.getPrice_per_point() * quantity;
    }
    public void setTotal_price(double total_price){
        this.total_price = total_price;
    }

    @Override
    public boolean equals(Object object){
        return super.equals(object);
    }

}
