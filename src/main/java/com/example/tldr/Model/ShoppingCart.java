package com.example.tldr.Model;
import java.beans.JavaBean;
import java.util.ArrayList;

@JavaBean
public class ShoppingCart
{
    private ArrayList<ProductDataset> cart;
    private double total;
    private int id, accID;
    private boolean approved = false;

    public ShoppingCart(){
        this.cart = new ArrayList<>();
        this.total = 0;
    }
    public ShoppingCart(int id){
        this.cart = new ArrayList<>();
        this.total = 0;
        this.id = id;
    }
    public ShoppingCart(ArrayList<ProductDataset> cart){
        this.cart = cart;
        this.total = 0;
        for(ProductDataset product: cart)
        {
            this.total += product.getTotal_price();
        }
    }
    public ArrayList<ProductDataset> getCart() {return cart;}

    public void setCart(ArrayList<ProductDataset> cart) {this.cart = cart;}

    public double getTotal() {return total;}

    public void setTotal(double total) {this.total = total;}

    public void updateQuantityAt(int index,int quantity){
        total -= cart.get(index).getTotal_price();
        cart.get(index).setQuantity(quantity);
        total += cart.get(index).getTotal_price();
    }

    public void setId(int id) {this.id = id;}

    public int getId() { return this.id;}

    public void removeDataSet(int index){
        total -= cart.get(index).getTotal_price();
        cart.remove(index);

    }

    public void addDataSet(ProductDataset dataset){
        cart.add(dataset);
        total += dataset.getTotal_price();
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public boolean containsDataset(Dataset dataset){
        for(ProductDataset pd:cart){
            if(pd.equals(dataset)){
                return true;
            }
        }
        System.out.println();
        return false;
    }

    public int getNewMaximumQuantity(Dataset dataset){
        for(ProductDataset pd:cart){
            if(pd.equals(dataset)){
                return dataset.getMaxQuantity()-pd.getQuantity();
            }
        }
        return -1;
    }

    public boolean isApproved(){
        return approved;
    }

    public void setApproved(boolean status){
        this.approved = status;
    }
}
