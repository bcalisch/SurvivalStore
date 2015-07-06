package com.survivalstore.user;

import java.text.NumberFormat;

/**
 * Created by benjamin on 7/1/15.
 */
public class Wallet {
    private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private double cash ;

    public Wallet(){
        //Min + (int)(Math.random() * ((Max - Min) + 1))
        int min = 100;
        cash = min + (int)(Math.random()* 4001);

    }

    public double getCash() {
        return cash;
    }

    public void printWallet(){
        System.out.println("You currently have "+ currencyFormat.format(cash)+" in your wallet at this time.");
    }

    /**
     * Takes out the given amount from the wallet.
     * @param amount the amount about to be taken from the wallet.
     */
    public void takeOut(double amount) {
        if (amount<= this.cash) {
            this.cash -= amount;
        }
    }
}
