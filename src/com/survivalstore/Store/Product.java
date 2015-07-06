package com.survivalstore.store;


/**
 * Create an object for each product, and create a list of objects by using CSV parser.
 */
public class Product {
    private String category;
    private String name;
    private double price;
    private int stockNum;
    private int id;


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

    public void addStock(int stockNumber) {
        stockNum += stockNumber;
    }
    public void removeStock(int stockNumber) {
        stockNum -= stockNumber;
    }


}
