package com.survivalstore; /**
 * This is our main class for Setting up the menu of products.
 */

public class StoreInterface {
    public static void main(String[] args) {System.out.println("Loading com.survivalstore.Store... Please Wait...");
        Store store = new Store();
        Wallet wallet = new Wallet();
        Cart cart = new Cart();
        store.sortProducts("1");

        cart.addCartItems(Store.getInventoryMap(3), 5);
        cart.addCartItems(Store.getInventoryMap(20), 10);

        store.printInventory(cart.getCartItems());
        System.out.println();

        store.printInventory(store.getInventory());
       // System.out.println(user1.getWallet().getCash());

    }




}
