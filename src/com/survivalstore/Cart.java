package com.survivalstore;

import java.text.NumberFormat;
import java.util.ArrayList;


/**
 * The Cart holds the items the customer added from the
 */
public class Cart {
    private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private ArrayList<Product> cartItems = new ArrayList<>();

    public ArrayList<Product> getCartItems() {
        return cartItems;
    }

    public void addCartItems(int item, int itemNumber) {
        int itemIndex = item - 1;

        if (Store.getInventory().size() <= item){
            System.out.println("Item not found in inventory!");
            return;
        }
        Product inventoryItem = Store.getInventory().get(itemIndex);
        if (itemNumber <= inventoryItem.getStockNum()) {
            int stockNum = inventoryItem.getStockNum();
            Product itemForCart = Product.cloneProduct(inventoryItem);
            Integer cartItemIndex = findCartItems(itemIndex);

            itemForCart.setStockNum(itemNumber);

            if (cartItemIndex != null) {
                int productNumber = cartItems.get(cartItemIndex).getStockNum();
                cartItems.get(cartItemIndex).setStockNum(productNumber + itemNumber);
            } else {
                cartItems.add(itemForCart);
            }
            inventoryItem.setStockNum(stockNum - itemNumber);
        }
        else{
            System.out.println("Item is out of stock!");
        }

    }

    private Integer findCartItems(int itemIndex) {
        Integer cartItemIndex = null;
        int index;
        for (Product item : cartItems) {
            index = item.getId();
            if (index == itemIndex) {
                cartItemIndex = cartItems.indexOf(item);
            }

        }

        return cartItemIndex;
    }


    public void removeCartItems(int item, int itemNumber) {
        Integer itemIndex = findCartItems(item);
        if (itemIndex != null) {
            Product itemForCartRemoval = cartItems.get(itemIndex);
            itemForCartRemoval.setStockNum((itemForCartRemoval.getStockNum() - itemNumber));
            int stockNumber = itemForCartRemoval.getStockNum();
            if (stockNumber <= 0) {
                cartItems.remove((int)itemIndex);
            }

            if (stockNumber <0){
                System.out.println("Just an FYI, you tried to take out more items than were in your cart.  No bother, as we have simply removed the entire item.  Have fun!");
            }

        } else {
            System.out.println("That ID is not in your cart.  Please try a different ID");
        }
    }

    /**
     * This method checks to see if the user has enough money.
     * if they do, the cart is cleared, and the total amount
     * is taken from the wallet.
     *
     * @param wallet is the wallet object the user has.
     */
    public void checkout(Wallet wallet){
        double total = cartTotalPrice();
        printCartItems();
        if(hasEnoughMoney(wallet.getCash())){


            wallet.takeOut(total);

            cartItems.clear();

            System.out.println("You have currently purchased these items for "+ total+".  They are ready for pickup!  Have a great day!");
        }
        else{
            System.out.println("You don't have enough money, BUM!!!!");
        }
    }

    /**
     * Totals up the items in the cart
     *
     * @return returns a double value.
     */
    public double cartTotalPrice(){
        double total = 0.00;
        for (Product item : cartItems){
            total += (item.getPrice() * item.getStockNum());
        }
     return total;
    }
    private boolean hasEnoughMoney(double cash) {
        int totalCost=0;
        for (Product item: cartItems){
            totalCost += (item.getPrice() * item.getStockNum());
        }
        if (totalCost <= cash){
            return true;
        }
        return false;
    }

    /**
     * Prints Cart items
     */
    public void printCartItems() {
        int size = cartItems.size();


        if (size > 0) {
            Store.printInventory(cartItems);
            System.out.println("Total for cart: " + currencyFormat.format(cartTotalPrice()));
            System.out.println();
        } else {
            System.out.println("Cart is empty!!!");
            System.out.println();
        }
    }
}
