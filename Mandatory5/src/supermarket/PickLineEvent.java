package supermarket;

import eventsim.Event;
import eventsim.EventSim;

/**
 * Created by Sindre on 04.04.2017.
 * #Event #3
 * Select a checkoutPoint and join the queue.
 */
public class PickLineEvent extends Event {

    Customer customer;

    //The customer should enter a queue when he's done shopping.
    public PickLineEvent(Customer customer) {
        super(EventSim.getClock() + customer.endShoppingTime);
        this.customer = customer;
    }

    // Find a queue


    @Override
    public Event happen() {

        return new
    }
}
