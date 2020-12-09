package br.com.chagas.ecommerce.order;

import br.com.chagas.ecommerce.framework.CrudService;

public interface OrderService extends CrudService<Order, Long> {

    Boolean approveOrder(Long orderId);

    Boolean cancelOrder(Long orderId);
}
