package supermarket;

import eventsim.Event;
import eventsim.EventSim;


/**
 * Created by Sindre on 04.04.2017.
 * Event #4
 */
public class CheckoutEvent extends Event {
    Customer customer;


    public CheckoutEvent(int checkOutTime, Customer customer) {
        super(checkOutTime);
        this.customer = customer;
    }


    // Remove that customer from the line Queue.
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
