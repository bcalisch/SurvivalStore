package com.survivalstore.store;

import com.survivalstore.input_output.LoadInventory;
import com.survivalstore.user.Cart;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Holds a list of product objects and allows for sorting and purchasing.
 */
public class Store {
    private static ArrayList<Product> inventory;
    private static ArrayList<Product> purchasedItems = new ArrayList<>();

    public Store() {
        inventory = LoadInventory.loadInventory();
    }

    /**
     *
     * @return
     */

     public static ArrayList<Product> getPurchasedItems() {
        return purchasedItems;
    }
    public static Product getInventoryItemIndex(int indexNum) {
        return inventory.get(indexNum);
    }



    /**
     * based on what string is sent to the method, a different comparator is called
     * to sort the arraylist of products
     * @param input The input should be a string of 0, 1, or 2.  Otherwise,
     *              it doesn't sort at all.
     */
    public void sortProducts(String input) {
        switch (input) {
            case "Name":
                Collections.sort(inventory, new NameComparator());
                Print.printInventory(inventory);
                break;
            case "Category":
                Collections.sort(inventory, new CategoryComparator());
                Print.printInventory(inventory);
                break;
            case "Price":
                Collections.sort(inventory, new PriceComparator());
                Print.printInventory(inventory);
                break;
            default:
                System.out.println("You have supplied a response that I don't know how to deal with, please try again");

                break;
        }




        Collections.sort(inventory, new Store.IDComparator());
    }

    public static void purchase(Product item) {
        Integer itemIndex = Cart.findItem(purchasedItems, item.getId());
        if(itemIndex != null){
            purchasedItems.get(itemIndex).addStock(item.getStockNum());
        }else{
            Product purchasedProduct = Product.cloneProduct(item);
            purchasedItems.add(purchasedProduct);
        }

    }

    public static ArrayList<Product> getPopularItems() {
        ArrayList<Product> popularItems = new ArrayList<>();
        for (Product item : purchasedItems){
            if (item.getStockNum() > 4){
                popularItems.add(item);
            }
            if (popularItems.size() == 0){
                System.out.println("There are no popular Items!");
            }
        }

        return popularItems;
    }

    public void searchInventory(String searchString) {
        ArrayList<Product> searchList = new ArrayList<>();
        for(Product item: inventory){
            if (item.getName().toUpperCase().replaceAll(" ", "").contains(searchString)){
                searchList.add(item);
            }

        }
        if(searchList.size() > 0) {
            Print.printInventory(searchList);
        }
        else{
            System.out.println("No match!!!");
        }
    }

    private static class NameComparator implements Comparator<Product> {
        @Override
        public int compare(Product i1, Product i2) {
            return i1.getName().compareTo(i2.getName());
        }
    }

    private static class CategoryComparator implements Comparator<Product> {
        @Override
        public int compare(Product i1, Product i2) {
            return i1.getCategory().compareTo(i2.getCategory());
        }
    }

    private static class PriceComparator implements Comparator<Product> {
        @Override
        public int compare(Product i1, Product i2) {
            if (i1.getPrice() < i2.getPrice()) return -1;
            return 1;
        }
    }
    private static class IDComparator implements Comparator<Product> {
        @Override
        public int compare(Product i1, Product i2) {
            if (i1.getId() < i2.getId()) return -1;
            return 1;
        }
    }

    /**
     * gets the current inventory list
     * @return:  returns array list of products
     */
    public static ArrayList<Product> getInventory() {
        return inventory;
    }



}
