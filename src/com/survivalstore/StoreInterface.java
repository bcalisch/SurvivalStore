package com.survivalstore;


import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is our main class for Setting up the menu of products.
 */

public class StoreInterface {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Loading com.survivalstore.Store... Please Wait...");
        Store store = new Store();
        Wallet wallet = new Wallet();
        Cart cart = new Cart();
        boolean exit = false, back = false;
        printWelcome();
        while (!(exit)) exit = mainMenu(store, wallet, cart);

    }

    private static boolean mainMenu(Store store, Wallet wallet, Cart cart) {
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        String initialChoice;
        boolean back;
        printMenu();
        initialChoice = in.nextLine();
        back = false;
        switch (initialChoice.toUpperCase()) {
            case ("0"):

                Store.printInventory(Store.getInventory());
                System.out.println();
                back = false;
                while (!back) back = addToCartInterface(in, cart);
                break;
            case ("1C"):
            case ("1 -C"): {
                store.sortProducts("1");
                while (!back) back = addToCartInterface(in, cart);
                break;
            }
            case ("1N"):
            case ("1 -N"): {
                store.sortProducts("0");
                while (!back) back = addToCartInterface(in, cart);

                break;
            }
            case ("1P"):
            case ("1 -P"): {
                store.sortProducts("2");
                while (!back) back = addToCartInterface(in, cart);
                break;
            }
            case ("2"):
                cart.printCartItems();
                while (!back) {
                    back = cartInterface(cart, wallet);
                }
                break;
            case ("3"):
                wallet.printWallet();
                break;
            case ("4"):
                exit = true;
                System.out.println("Good Bye!");
                break;
            default: {
                System.out.println("I don't understand, please try again");
            }
        }
        return exit;
    }

    private static boolean cartInterface( Cart cart, Wallet wallet) {
        Scanner Q1 = new Scanner(System.in), Q2 = new Scanner(System.in), Q3 = new Scanner(System.in);
        boolean back = false;
        int prodID = 0, prodAmount = 0;
        if (cart.getCartItems().size() > 0) {
            String printChoice ="";
            System.out.println("Options: ");
            System.out.println("        To remove an item from your cart, type 'remove' ");
            System.out.println("        To Check out your cart, type 'purchase'.  If you have enough money, we will purchase the items for you");
            System.out.println("        To go back to the main menu, type 'back'");
            printChoice = Q1.nextLine().toUpperCase();
            switch (printChoice) {
                case ("PURCHASE"): {
                    cart.checkout(wallet);
                    break;
                }
                case ("BACK"): {
                    back = true;
                    break;
                }
                case ("REMOVE"): {
                    try {

                        System.out.println("Which product would you like to remove from your cart?");
                        prodID = Q2.nextInt();
                        System.out.println("How many would you like to remove?");
                        prodAmount = Q3.nextInt();
                        cart.removeCartItems(prodID, prodAmount);
                        System.out.println("Items successfully removed.");
                    } catch (NumberFormatException | InputMismatchException e) {
                        System.out.println("That was not a valid integer value.  Please try again");
                    }
                    break;

                }
                default:
                    System.out.println("I don't understand.  Please try again.");
            }
        } else {
            back = true;
        }

        return back;


    }

    private static boolean addToCartInterface(Scanner in, Cart cart) {
        boolean back = false;
        int prodID = 0;
        String printChoice;
        String itemAmount;
        System.out.println("Options: ");
        System.out.println("        To add an item to cart, type 'add -' + the product ID");
        System.out.println("        To go back to the main menu, type 'back'");
        printChoice = in.nextLine().toUpperCase();
        if (printChoice.contains("ADD -")) {
            printChoice = printChoice.substring(printChoice.indexOf('-') + 1, printChoice.length());
            try {
                prodID = Integer.parseInt(printChoice);
                System.out.println("How many would you like to purchase?");
                itemAmount = in.nextLine();
                int prodAmount = Integer.parseInt(itemAmount);
                cart.addCartItems(prodID, prodAmount);
                System.out.println("Item(s) successfully added to cart!");
            } catch (NumberFormatException e) {
                System.out.println("That was not a valid integer value.  Please try again");
                in.reset();
            }


        } else if (printChoice.contains("BACK")) {
            back = true;
        } else {
            System.out.println("I did not understand your request.  Please try again.");
        }
        return back;
    }

    private static void printMenu() {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   What would you like to do?");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   Here are some options...");
        System.out.println();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t (0)...............See list of products");
        System.out.println("  \t\t\t\t\t\t\t\t\t\t\t (1 -[C, N, P])....See list of products in order of (C)ategory, (N)ame, or (P)rice");
        System.out.println("  \t\t\t\t\t\t\t\t\t\t\t (2)...............View Shopping Cart");
        System.out.println("  \t\t\t\t\t\t\t\t\t\t\t (3)...............View Wallet");
        System.out.println("  \t\t\t\t\t\t\t\t\t\t\t (4)...............Exit Program");
    }

    private static void printWelcome() {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t──────▄▀▀▀▀▀▀▀▄───────");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t─────▐─▄█▀▀▀█▄─▌──────");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t─────▐─▀█▄▄▄█▀─▌──────");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t──────▀▄▄▄▄▄▄▄▀───────");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t─────▐▀▄▄▐█▌▄▄▀▌──────");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t──────▀▄▄███▄▄▀───────");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t█░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t█░░║║║╠─║─║─║║║║║╠─░░█");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t█░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█");
        System.out.println();
    }


}
