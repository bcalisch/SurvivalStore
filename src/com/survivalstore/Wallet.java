package com.survivalstore;

/**
 * Created by benjamin on 7/1/15.
 */
public class Wallet {
    private double cash;

    public Wallet(){
        //Min + (int)(Math.random() * ((Max - Min) + 1))
        int min = 100;
        cash = min + (int)(Math.random()* 4001);
    }

    public double getCash() {
        return cash;
    }

}
