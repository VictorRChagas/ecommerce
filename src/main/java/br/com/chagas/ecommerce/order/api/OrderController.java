package br.com.chagas.ecommerce.order.api;

import br.com.chagas.ecommerce.framework.CrudRestController;
import br.com.chagas.ecommerce.framework.CrudService;
import br.com.chagas.ecommerce.order.Order;
import br.com.chagas.ecommerce.order.OrderService;
import br.com.chagas.ecommerce.order.dto.OrderPersistDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController extends CrudRestController<Order, Long, OrderPersistDto> {

    private final OrderService service;
    private final OrderModelAssembler orderModelAssembler;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService service, OrderModelAssembler orderModelAssembler) {
        this.service = service;
        this.orderModelAssembler = orderModelAssembler;
    }

    @Override
    public CrudService<Order, Long> getService() {
        return service;
    }

    @Override
    public RepresentationModelAssembler<Order, EntityModel<Order>> getRepresentationModelAssembler() {
        return orderModelAssembler;
    }

    @PostMapping("approve/{id}")
    public ResponseEntity<Boolean> approveOrder(@PathVariable("id") Long orderId) {
        LOGGER.debug("Approving order id: {}", orderId);
        return ResponseEntity.ok(service.approveOrder(orderId));
    }

    @PostMapping("cancel-order/{id}")
    public ResponseEntity<Boolean> cancelOrder(@PathVariable("id") Long orderId) {
        LOGGER.debug("Cancelling order id: {}", orderId);
        return ResponseEntity.ok(service.cancelOrder(orderId));
    }
}
