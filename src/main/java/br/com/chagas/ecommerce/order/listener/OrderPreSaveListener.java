package br.com.chagas.ecommerce.order.listener;

import br.com.chagas.ecommerce.order.OrderStatus;
import br.com.chagas.ecommerce.order.events.PreSaveOrderEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OrderPreSaveListener {

    @EventListener
    public void setDefaultValues(PreSaveOrderEvent event) {
        var order = event.getOrder();
        if (Objects.isNull(order.getId())) {
            order.setOrderStatus(OrderStatus.PENDING);
        }
    }
}
