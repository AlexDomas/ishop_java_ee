/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.model;

import java.io.Serializable;
import main.java.by.bntu.poisit.entity.Product;


public class ShoppingCartItem implements Serializable{
    
    private static final long serialVersionUID = 6436798264138502851L;
    private Product product;
    private int count;
    
    public ShoppingCartItem(){
        super();
    }

    public ShoppingCartItem(Product product, int count) {
        super();
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("ShoppingCartItem [product=%s, count=s%]", product, count);
    }

    
    
    
}
