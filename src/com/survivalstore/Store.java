package com.survivalstore;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Holds a list of product objects and allows for sorting and purchasing.
 */
public class Store {
    private static ArrayList<Product> inventory;
    private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    public Store() {
        inventory = LoadInventory.loadInventory();
    }

    /**
     *
     * @return
     */


    /**
     * Prints out a list of Product items in a specific format
     * @param inventory :  an array List of products
     */
    public static void printInventory(ArrayList<Product> inventory) {
        int spaceNum , size = inventory.size();
        StringBuilder space = new StringBuilder(" ");
        for (int i = 0; i < size; i++) {

            if (i%20 == 0){
                printHeader(((i/20) +1));
            }
            Product item = inventory.get(i);
            spaceNum = 30 - item.getCategory().length();
            makeSpaces(spaceNum, space);
            System.out.print(item.getCategory() + space);

            spaceNum = 62 - item.getName().length();
            makeSpaces(spaceNum, space);
            System.out.print(item.getName() + space);

            spaceNum = 27 - (currencyFormat.format(item.getPrice())).length();
            makeSpaces(spaceNum, space);
            System.out.print(currencyFormat.format(item.getPrice()) + "" + space);

            spaceNum = 17 - (Integer.toString(item.getStockNum()).length());
            makeSpaces(spaceNum, space);
            System.out.print(item.getStockNum() + "" + space);

            System.out.print(item.getId() + "\n");

        }
        System.out.println();
    }

    private static void printHeader(int pageNumber) {
        System.out.println();
        System.out.println("Page Number: "+ pageNumber);
        System.out.println();
        System.out.println("Category\t\t\t\t\t\t\t\t Name " + "\t\t\t\t\t\t\t\t\t\t\t\tPrice\t\t\t\t\t Count\t\t\tProduct ID");
        System.out.println("--------\t\t\t\t\t\t\t\t ----- " + "\t\t\t\t\t\t\t\t\t\t\t\t-----\t\t\t\t\t-------\t\t\t----------");

    }

    private static void makeSpaces(int spaceNum, StringBuilder space) {
        space.delete(0, space.length());
        for (int j = 0; j < spaceNum; j++) {
            space.append(" ");
        }
    }

    /**
     * based on what string is sent to the method, a different comparator is called
     * to sort the arraylist of products
     * @param input The input should be a string of 0, 1, or 2.  Otherwise,
     *              it doesn't sort at all.
     */
    public void sortProducts(String input) {
        switch (input) {
            case "0":
                Collections.sort(inventory, new NameComparator());
                printInventory(inventory);
                break;
            case "1":
                Collections.sort(inventory, new CategoryComparator());
                printInventory(inventory);
                break;
            case "2":
                Collections.sort(inventory, new PriceComparator());
                printInventory(inventory);
                break;
            default:
                System.out.println("You have supplied a response that I don't know how to deal with, please try again");

                break;
        }


        Collections.sort(inventory, new Store.IDComparator());
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
