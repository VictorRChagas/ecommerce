package br.com.chagas.ecommerce.payment.api;

import br.com.chagas.ecommerce.framework.CrudRestController;
import br.com.chagas.ecommerce.framework.CrudService;
import br.com.chagas.ecommerce.payment.Payment;
import br.com.chagas.ecommerce.payment.PaymentService;
import br.com.chagas.ecommerce.payment.dto.PaymentPersistDto;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController extends CrudRestController<Payment, Long, PaymentPersistDto> {

    private final PaymentService paymentService;
    private final PaymentModelAssembler paymentModelAssembler;

    public PaymentController(ModelMapper modelMapper, PaymentService paymentService, PaymentModelAssembler paymentModelAssembler) {
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
}
