package com.survivalstore.store;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by benjamin on 7/6/15.
 */
public class Print {
    private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    /**
     * Prints out a list of Product items in a specific format
     * @param inventory :  an array List of products
     */
    public static void printInventory(ArrayList<Product> inventory) {
        int spaceNum , size = inventory.size();
        StringBuilder space = new StringBuilder(" ");
        for (int i = 0; i < size; i++) {

            if (i%20 == 0){
                printHeader(((i / 20) + 1));
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

    private static void makeSpaces(int spaceNum, StringBuilder space) {
        space.delete(0, space.length());
        for (int j = 0; j < spaceNum; j++) {
            space.append(" ");
        }
    }
    private static void printHeader(int pageNumber) {
        System.out.println();
        System.out.println("Page Number: "+ pageNumber);
        System.out.println();
        System.out.println("Category\t\t\t\t\t\t\t\t Name " + "\t\t\t\t\t\t\t\t\t\t\t\tPrice\t\t\t\t\t Count\t\t\tProduct ID");
        System.out.println("--------\t\t\t\t\t\t\t\t ----- " + "\t\t\t\t\t\t\t\t\t\t\t\t-----\t\t\t\t\t-------\t\t\t----------");

    }
}
