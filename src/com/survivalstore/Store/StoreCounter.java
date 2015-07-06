package com.survivalstore.store;


import com.survivalstore.menu.MenuQuestions;
import com.survivalstore.menu.StoreMenu;
import com.survivalstore.user.Wallet;
import com.survivalstore.user.Cart;


/**
 * This is our main class for Setting up the menu of products.
 */

public class StoreCounter {
    public static void main(String[] args) {
        System.out.println("Loading com.survivalstore.Store.Store... Please Wait...");
        Store store = new Store();
        Wallet wallet = new Wallet();
        Cart cart = new Cart();
        boolean exit = false;
        StoreMenu.printWelcome();
        while (!(exit)) exit = MenuQuestions.mainMenu(store, wallet, cart);

    }


}
