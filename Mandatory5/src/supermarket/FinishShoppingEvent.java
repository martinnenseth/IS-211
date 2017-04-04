package supermarket;

import eventsim.Event;

/**
 * Created by Sindre on 04.04.2017.
 */
public class FinishShoppingEvent extends Event{

    Customer customer;


    public FinishShoppingEvent(Customer customer){
        super(customer.endShoppingTime);
        this.customer = customer;

    }
    @Override
    public Event happen(){
        return new PickLineEvent(customer);
    }
}
