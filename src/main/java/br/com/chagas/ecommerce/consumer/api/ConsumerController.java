package br.com.chagas.ecommerce.consumer.api;

import br.com.chagas.ecommerce.consumer.Consumer;
import br.com.chagas.ecommerce.consumer.ConsumerService;
import br.com.chagas.ecommerce.consumer.dto.ConsumerPersistDto;
import br.com.chagas.ecommerce.framework.CrudRestController;
import br.com.chagas.ecommerce.framework.CrudService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController extends CrudRestController<Consumer, Long, ConsumerPersistDto> {

    private final ConsumerService consumerService;
    private final ConsumerModelAssembler consumerModelAssembler;

    public ConsumerController(ConsumerService consumerService, ConsumerModelAssembler consumerModelAssembler) {
        this.consumerService = consumerService;
        this.consumerModelAssembler = consumerModelAssembler;
    }

    @Override
    public CrudService<Consumer, Long> getService() {
        return consumerService;
    }

    @Override
    public RepresentationModelAssembler<Consumer, EntityModel<Consumer>> getRepresentationModelAssembler() {
        return consumerModelAssembler;
    }
}
