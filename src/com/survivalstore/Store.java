package com.survivalstore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Holds a list of product objects and allows for sorting and purchasing.
 */
public class Store {
    private static ArrayList<Product> inventory;
    private static HashMap<Integer, Product> inventoryMap = new HashMap<>();
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    //private String fileName;


    public Store() {
        inventory = loadInventory();
    }

    public static ArrayList<Product> loadInventory() {
        ArrayList<Product> inventory = new ArrayList<Product>() {
        };
        String fileName = "resources/inventory.csv";
        String line;


        //Deal With the file
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {
            int prodID = 0;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                inventory.add(Product.convertToProduct(line));
               // inventory.get(prodID).setId(++prodID);

                inventoryMap.put((++prodID), inventory.get(prodID-1));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Finished with the file


        return inventory;

    }

    /**
     * Prints the inventory out formatted for easy viewing.
     */
    public void printInventory(ArrayList<Product> inventory) {
        int spaceNum;
        StringBuilder space = new StringBuilder(" ");
       // printHeader(1);
        for (int i = 0; i < inventory.size(); i++) {

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

    private void printHeader(int pageNumber) {
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
        if (input.equals("0")) {
            Collections.sort(inventory, new Store.NameComparator());
            this.printInventory(inventory);
        } else if (input.equals("1")) {
            Collections.sort(inventory, new Store.CategoryComparator());
            this.printInventory(inventory);
        }else if (input.equals("2")) {
            Collections.sort(inventory, new Store.PriceComparator());
            this.printInventory(inventory);
        }else{
            System.out.println("You have supplied a response that we don't know how to deal with, please try again");

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

    public ArrayList<Product> getInventory() {
        return inventory;
    }


    public static Product getInventoryMap(int key) {
        Product thisProduct = inventoryMap.get(key);
      // return inventory.get(inventoryMap.get(key));
      // System.out.println(thisProduct.getName()+ " is " + thisProduct.getPrice());

        return inventoryMap.get(key);
    }
}
