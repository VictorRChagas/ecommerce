package br.com.chagas.ecommerce.delivery.api;

import br.com.chagas.ecommerce.delivery.Delivery;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class DeliveryModelAssembler implements RepresentationModelAssembler<Delivery, EntityModel<Delivery>> {

    @Override
    public EntityModel<Delivery> toModel(Delivery entity) {
        return null;
    }
}
