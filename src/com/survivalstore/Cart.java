package com.survivalstore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Cart holds the items the customer added from the
 */
public class Cart {
    private ArrayList<Product> cartItems = new ArrayList<>();

    public ArrayList<Product> getCartItems() {
        return cartItems;
    }

    public void addCartItems(Product cartItems, int itemNumber) {
       // for (int i = 0; i < itemNumber; i++) {
        Product thisProduct = cartItems.cloneProduct();
        this.cartItems.add(thisProduct);
            thisProduct.setStockNum(itemNumber);
        //}
    }
    public void displayCart() {
        for(Product items: cartItems){

        }
    }
    

}
