package br.com.chagas.ecommerce.manufacturer.api;

import br.com.chagas.ecommerce.manufacturer.Manufacturer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerModelAssembler implements RepresentationModelAssembler<Manufacturer, EntityModel<Manufacturer>> {

    @Override
    public EntityModel<Manufacturer> toModel(Manufacturer entity) {
        return null;
    }
}
