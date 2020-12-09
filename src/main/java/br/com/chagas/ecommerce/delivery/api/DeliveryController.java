package br.com.chagas.ecommerce.delivery.api;

import br.com.chagas.ecommerce.delivery.Delivery;
import br.com.chagas.ecommerce.delivery.DeliveryService;
import br.com.chagas.ecommerce.delivery.dto.DeliveryPersistDto;
import br.com.chagas.ecommerce.framework.CrudRestController;
import br.com.chagas.ecommerce.framework.CrudService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/delivery")
public class DeliveryController extends CrudRestController<Delivery, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Delivery.class);
    private final DeliveryService service;
    private final DeliveryModelAssembler deliveryModelAssembler;
    private final ModelMapper modelMapper;

    public DeliveryController(DeliveryService service, DeliveryModelAssembler deliveryModelAssembler, ModelMapper modelMapper) {
        this.service = service;
        this.deliveryModelAssembler = deliveryModelAssembler;
        this.modelMapper = modelMapper;
    }

    @Override
    public CrudService<Delivery, Long> getService() {
        return service;
    }

    @Override
    public RepresentationModelAssembler<Delivery, EntityModel<Delivery>> getRepresentationModelAssembler() {
        return deliveryModelAssembler;
    }

    @Override
    public Class<?> getClazz() {
        return this.getClass();
    }

    @PostMapping
    public ResponseEntity<EntityModel<Delivery>> save(@NonNull @Valid @RequestBody DeliveryPersistDto dto) {
        LOGGER.debug("Saving new Delivery");
        var delivery = modelMapper.map(dto, Delivery.class);
        var entityModel = deliveryModelAssembler.toModel(service.save(delivery));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
