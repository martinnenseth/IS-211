/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.EventSim;


/**
 *
 * @author evenal
 */
public class Customer {
    // customer will pick a random number of products between these two values
    public static final int MAX_PRODUCTS = 500;
    public static final int MIN_PRODUCTS = 1;

    // customer will spend ranom amount of time between these values before
    // going to check out
    public static final int MAX_SHOP_TIME = 50;
    public static final int MIN_SHOP_TIME = 1;

    SuperMarket shop;
    String name;

    int beginShoppingTime; //start
    int shoppingDuration;
    int numProducts;
    int endShoppingTime; //Done shopping
    int queueWaitDuration; //In line
    int checkoutDuration; // Time it takes for a customer to be handled by store employee
    int leaveTime;



    int checkoutTime;   // Time when the customer will be removed from the queue to be handled by employee.


    public int getNumProducts() {
        return numProducts;
    }
    public void setCheckoutTime(int checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public int getCheckoutTime() {
        return checkoutTime;
    }

    public int getCheckoutDuration() {
        return checkoutDuration;
    }

    public void setCheckoutDuration(int checkoutDuration) {
        this.checkoutDuration = checkoutDuration;
    }


    public Customer(SuperMarket shop, int i) {
        this.shop = shop;
        name = "Cust" + i;
        beginShoppingTime = i;
        numProducts = EventSim.nextInt(MIN_PRODUCTS, MAX_PRODUCTS);
        shoppingDuration = EventSim.nextInt(MIN_SHOP_TIME, MAX_SHOP_TIME);
        endShoppingTime = beginShoppingTime + shoppingDuration;
    }
}
