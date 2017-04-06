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

    /**
     * A type 3 event do not cause any new events, except if the checkout point is not serving
     * any customer, and the queue is empty. In that case a type 4 event will happen a short
     * time later. (If the checkout wasn't doing anything it wakes up when the customer joins the queue.
     * @return
     */
    @Override
    public Event happen() {
        Checkout.line.add(customer);
        return new CheckoutEvent(calcCheckoutTime(), customer);
    }
}
