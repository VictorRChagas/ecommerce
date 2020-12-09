package br.com.chagas.ecommerce.order.events;

import br.com.chagas.ecommerce.order.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class PreSaveOrderEvent extends ApplicationEvent {

    @Getter
    private Order order;

    public PreSaveOrderEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }
}
