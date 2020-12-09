package br.com.chagas.ecommerce.payment.api;

import br.com.chagas.ecommerce.framework.CrudRestController;
import br.com.chagas.ecommerce.framework.CrudService;
import br.com.chagas.ecommerce.payment.Payment;
import br.com.chagas.ecommerce.payment.PaymentService;
import br.com.chagas.ecommerce.payment.dto.PaymentPersistDto;
import br.com.chagas.ecommerce.product.api.ProductController;
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
@RequestMapping("/payment")
public class PaymentController extends CrudRestController<Payment, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;
    private final PaymentModelAssembler paymentModelAssembler;

    public PaymentController(ModelMapper modelMapper, PaymentService paymentService, PaymentModelAssembler paymentModelAssembler) {
        this.modelMapper = modelMapper;
        this.paymentService = paymentService;
        this.paymentModelAssembler = paymentModelAssembler;
    }

    @Override
    public CrudService<Payment, Long> getService() {
        return paymentService;
    }

    @Override
    public RepresentationModelAssembler<Payment, EntityModel<Payment>> getRepresentationModelAssembler() {
        return paymentModelAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Payment>> save(@NonNull @Valid @RequestBody PaymentPersistDto dto) {
        LOGGER.debug("Saving new Payment");
        var payment = modelMapper.map(dto, Payment.class);
        var entityModel = paymentModelAssembler.toModel(paymentService.save(payment));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
