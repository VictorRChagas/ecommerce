package br.com.chagas.ecommerce.order;

import br.com.chagas.ecommerce.order.dto.OrderPersistDto;

public interface OrderConverter {

    Order buildOrder(OrderPersistDto orderPersistDto);
}
