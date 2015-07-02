package com.survivalstore; /**
 * This is our main class for Setting up the menu of products.
 */

public class StoreInterface {
    public static void main(String[] args) {System.out.println("Loading com.survivalstore.Store... Please Wait...");
        Store store = new Store();
        Wallet wallet = new Wallet();
        Cart cart = new Cart();
        store.sortProducts("0");
        store.sortProducts("1");
        store.sortProducts("2");


        cart.addCartItems(3, 5);
        cart.addCartItems(20, 10);
        cart.addCartItems(3, 5);

        store.printInventory(cart.getCartItems());
        System.out.println();

        cart.removeCartItems(3, 6);
        cart.removeCartItems(20, 9);

        store.printInventory(cart.getCartItems());
        

       // store.printInventory(store.getInventory());
       // System.out.println(user1.getWallet().getCash());

    }




}
