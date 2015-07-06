package com.survivalstore.input_output;

import com.survivalstore.store.Product;

/**
 * Class to convert Product from String.
 */
public class ConvertToProduct {
    /**
     * this converts the CSV string line to a new Product
     *
     * @param line string that contains all the product information
     * @return returns the product as an object back to the caller.
     */
    public static Product convertToProduct(String line) {
        String[] row = line.split(",");
        Product item = new Product();
        String thisLine;
        for (int i = 0; i < row.length; i++) {
            switch (i) {
                case (0):
                    item.setCategory(row[i]);
                    break;
                case (1):
                    item.setName(row[i]);
                    break;
                case (2):
                    thisLine = row[i];
                    if (thisLine.contains("\"")){
                        thisLine +=row[++i];
                        item.setPrice(Double.parseDouble(thisLine.replaceAll("\\$", "").replaceAll("\"", "").replaceAll(",","")));
                        item.setStockNum(Integer.parseInt(row[++i]));
                        break;
                    }else {
                        item.setPrice (Double.parseDouble(row[i].replaceAll("\\$", "").replaceAll("\"", "")));
                    }
                    break;
                case (3):
                    item.setStockNum(Integer.parseInt(row[i]));
            }
        }

        item.setId( Product.createID());
        return item;
    }
}
