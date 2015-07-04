package com.survivalstore;


/**
 * Create an object for each product, and create a list of objects by using CSV parser.
 */
public class Product {
    private String category;
    private String name;
    private double price;
    private int stockNum;
    private int id;


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
                    item.category = row[i];
                    break;
                case (1):
                    item.name = row[i];
                    break;
                case (2):
                    thisLine = row[i];
                    if (thisLine.contains("\"")){
                        thisLine +=row[++i];
                        item.price = Double.parseDouble(thisLine.replaceAll("\\$", "").replaceAll("\"", "").replaceAll(",",""));
                        item.stockNum = Integer.parseInt(row[++i]);
                        break;
                    }else {
                        item.price = Double.parseDouble(row[i].replaceAll("\\$", "").replaceAll("\"", ""));
                    }
                    break;
                case (3):
                    item.stockNum = Integer.parseInt(row[i]);
            }
        }

        item.id = createID();
        return item;
    }

    private static int idCounter = 1;

    /**
     * ID creation
     * @return
     */
    public static synchronized int createID() {
        return idCounter++;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * A method to copy one product to another
     * @param toBeCloned the Product that needs to be copied
     * @return returns a Product that is the copy.  this copy
     * can now be changed and so on.
     */
    public static Product cloneProduct(Product toBeCloned){
        Product clone = new Product();

        clone.setCategory(toBeCloned.getCategory());
        clone.setId(toBeCloned.getId());
        clone.setName(toBeCloned.getName());
        clone.setStockNum(toBeCloned.getStockNum());
        clone.setPrice(toBeCloned.getPrice());


        return clone;
    }

}
