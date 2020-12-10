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
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController extends CrudRestController<Delivery, Long, DeliveryPersistDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Delivery.class);
    private final DeliveryService service;
    private final DeliveryModelAssembler deliveryModelAssembler;

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
}
