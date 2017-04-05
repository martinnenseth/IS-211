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
        super(customer.endShoppingTime);
        this.customer = customer;
    }



    /**
     * A type 3 event do not cause any new events, except if the checkout point is not serving
     * any customer, and the queue is empty. In that case a type 4 event will happen a short
     * time later. (If the checkout wasn't doing anything it wakes up when the customer joins the queue.
     * @return
     */
    @Override
    public Event happen() {
        Checkout.line.add(customer);
        return new CheckoutEvent(customer);
    }
}
