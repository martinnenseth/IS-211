package supermarket;

import eventsim.Event;

/**
 * Created by Sindre on 04.04.2017.
 */
public class CheckoutEvent extends Event {
    Customer customer;

    public CheckoutEvent(Customer customer) {
        super(customer.checkoutDuration);
        this.customer = customer;
    }

    @Override
    public Event happen(){
        return new EndShoppingEvent(customer);
    }
}
