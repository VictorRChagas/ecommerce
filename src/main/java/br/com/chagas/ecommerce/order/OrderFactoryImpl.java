package br.com.chagas.ecommerce.order;

import br.com.chagas.ecommerce.order.dto.OrderPersistDto;
import org.springframework.stereotype.Component;

@Component
public class OrderFactoryImpl implements OrderFactory {

    @Override
    public Order buildOrder(OrderPersistDto orderPersistDto) {
        return new Order(orderPersistDto);
    }
}
