package br.com.chagas.ecommerce.delivery;

import br.com.chagas.ecommerce.framework.CrudService;

public interface DeliveryService extends CrudService<Delivery, Long> {

    Delivery save(Delivery delivery);

}
