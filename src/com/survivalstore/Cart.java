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

    public void addCartItems(int item, int itemNumber) {
        if ((Store.getInventoryMap(item)) != null) {
            Product itemForCart = Store.getInventoryMap(item);


            if (cartItems.contains(itemForCart)) {

                itemForCart.setStockNum((itemForCart.getStockNum() + itemNumber));
            } else {
                itemForCart.setStockNum(itemNumber);
                cartItems.add(itemForCart);
            }

        } else {
            System.out.println("That ID does not match any items in the store, Please try a different ID");
        }
    }

    public void removeCartItems(int item, int itemNumber) {
        if (cartItems.contains(Store.getInventoryMap(item))) {
            Product itemForCartRemoval = Store.getInventoryMap(item);
            itemForCartRemoval.setStockNum((itemForCartRemoval.getStockNum() - itemNumber));
            if (itemForCartRemoval.getStockNum() <= 0) {
                cartItems.remove(itemForCartRemoval);
            }

        } else {
            System.out.println("That ID is not in your cart.  Please try a different ID");
        }
    }


}
