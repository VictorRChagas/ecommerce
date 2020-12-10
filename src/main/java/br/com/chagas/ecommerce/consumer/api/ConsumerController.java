package br.com.chagas.ecommerce.consumer.api;

import br.com.chagas.ecommerce.consumer.Consumer;
import br.com.chagas.ecommerce.consumer.ConsumerService;
import br.com.chagas.ecommerce.consumer.dto.ConsumerPersistDto;
import br.com.chagas.ecommerce.framework.CrudRestController;
import br.com.chagas.ecommerce.framework.CrudService;
import br.com.chagas.ecommerce.product.api.ProductController;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController extends CrudRestController<Consumer, Long, ConsumerPersistDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ModelMapper modelMapper;
    private final ConsumerService consumerService;
    private final ConsumerModelAssembler consumerModelAssembler;

    public ConsumerController(ModelMapper modelMapper, ConsumerService consumerService,
                              ConsumerModelAssembler consumerModelAssembler) {
        this.modelMapper = modelMapper;
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
