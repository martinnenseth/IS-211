package supermarket;

import eventsim.Event;
import eventsim.EventSim;


/**
 * Created by Sindre on 04.04.2017.
 * Event #4
 */
public class CheckoutEvent extends Event {
    Customer customer;

    int lol = calcCheckoutTime();

    public CheckoutEvent(Customer customer) {

        //calcCheckoutTime();
        super(new CheckoutEvent(customer).calcCheckoutTime());
        this.customer = customer;
    }

    // Simulates the customer standing in queue...
    //If queue is empty, go straight to checkout().
    public int calcCheckoutTime() {

        if (Checkout.line.size() >= 0) {
            // The total time all other customers before this one have been waiting is this one's queueWaitTime.
            for (Customer customer : Checkout.line) {
                customer.setCheckoutDuration(Checkout.PAY_DURATION + Checkout.PROD_DURATION * customer.getNumProducts());
                customer.queueWaitDuration = customer.queueWaitDuration + customer.getCheckoutDuration();                 //Get some awesome data for each customer that tells us how long the other customers have been waiting....
            }
        }
        else {
            customer.setCheckoutDuration(Checkout.PAY_DURATION + Checkout.PROD_DURATION * customer.getNumProducts());
            customer.queueWaitDuration = 0;}

        customer.setCheckoutTime(customer.queueWaitDuration + super.getTime());
        return customer.getCheckoutTime();
    }


    // Remove that customer from the line Queue.
    // Calculate the time cost of scanning all the customer products
    // Set time to be EventTime + customer's)
    public void checkOut()  {
        Customer customer = Checkout.line.remove();
        this.setTime(this.getTime() + customer.getCheckoutDuration()); //Set time in the event itself instead.

    }


    @Override
    public Event happen(){
        checkOut();
        return new EndShoppingEvent(customer);
    }
}