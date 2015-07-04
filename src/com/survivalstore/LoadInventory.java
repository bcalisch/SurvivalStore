package com.survivalstore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that loads the inventory.csv files
 */
public class LoadInventory {

    public static ArrayList<Product> loadInventory() {
        ArrayList<Product> inventory = new ArrayList<Product>() {
        };
        String fileName = "resources/inventory.csv";
        String line;


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
                inventory.get(prodID).setId(++prodID);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inventory;

    }
}
