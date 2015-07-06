package com.survivalstore.menu;

import com.survivalstore.store.Print;
import com.survivalstore.user.Cart;
import com.survivalstore.store.Store;
import com.survivalstore.user.Wallet;

import java.util.Scanner;

/**
 *Menu class to handle the admin side of things.
 */
public class AdminMenu {

    public static boolean adminLogin(Store store, Cart cart, Wallet wallet) {
        boolean back = false;
        Scanner askPassword = new Scanner(System.in);
        System.out.println("Please enter the admin password!");
        String password = askPassword.nextLine();
        if(password.equals("Admin")){
            System.out.println("You have entered in the correct password!!!");
            while(!back){
                back = adminInterface(store, cart, wallet);
            }
            back = true;
        }else{
            System.out.println("That is a bad password, please try again!");
        }

        return back;
    }

    private static boolean adminInterface(Store store, Cart cart, Wallet wallet) {
        boolean back = false;
        Scanner adminChoice = new Scanner(System.in);
        StoreMenu.printAdminMenu();
        String input = adminChoice.nextLine().toUpperCase();
        if(input.equals("0")){
            Print.printInventory(Store.getInventory());
        }else if(input.contains("1 -")){
            String searchString = input.substring(input.indexOf('-')+1, input.length()).replaceAll(" ", "");
            System.out.println("You tried to search for "+searchString);
            store.searchInventory(searchString);

        }else if(input.equals("2")){
            Print.printInventory(Store.getPurchasedItems());
        }else if(input.equals("3")){
            Print.printInventory(Store.getPopularItems());
        }else if(input.equals("4")){
            System.out.println("So long, my admin friend!");
            back = true;
        }
        return back;
    }
}
