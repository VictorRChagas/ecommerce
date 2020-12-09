package br.com.chagas.ecommerce.order.api;

import br.com.chagas.ecommerce.framework.CrudRestController;
import br.com.chagas.ecommerce.framework.CrudService;
import br.com.chagas.ecommerce.order.Order;
import br.com.chagas.ecommerce.order.OrderFactory;
import br.com.chagas.ecommerce.order.OrderService;
import br.com.chagas.ecommerce.order.dto.OrderPersistDto;
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
@RequestMapping("/order")
public class OrderController extends CrudRestController<Order, Long> {

    private final OrderService service;
    private final OrderModelAssembler orderModelAssembler;
    private final OrderFactory orderFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService service, OrderModelAssembler orderModelAssembler, OrderFactory orderFactory) {
        this.service = service;
        this.orderModelAssembler = orderModelAssembler;
        this.orderFactory = orderFactory;
    }

    @Override
    public CrudService<Order, Long> getService() {
        return service;
    }

    @Override
    public RepresentationModelAssembler<Order, EntityModel<Order>> getRepresentationModelAssembler() {
        return orderModelAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Order>> save(@NonNull @Valid @RequestBody OrderPersistDto orderPersistDto) {
        LOGGER.debug("Saving new order");
        var order = orderFactory.buildOrder(orderPersistDto);
        var entityModel = orderModelAssembler.toModel(service.save(order));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
