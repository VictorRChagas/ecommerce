package br.com.chagas.ecommerce.delivery.api;

import br.com.chagas.ecommerce.delivery.Delivery;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DeliveryModelAssembler implements RepresentationModelAssembler<Delivery, EntityModel<Delivery>> {

    @Override
    public EntityModel<Delivery> toModel(Delivery entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(DeliveryController.class).findById(entity.getId())).withSelfRel(),
                linkTo(methodOn(DeliveryController.class).findAll(null, null)).withSelfRel(),
                linkTo(methodOn(DeliveryController.class).deleteById(entity.getId())).withSelfRel());
    }
}
