package br.com.chagas.ecommerce.consumer.api;

import br.com.chagas.ecommerce.consumer.Consumer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ConsumerModelAssembler implements RepresentationModelAssembler<Consumer, EntityModel<Consumer>> {

    @Override
    public EntityModel<Consumer> toModel(Consumer entity) {
        return null;
    }
}
