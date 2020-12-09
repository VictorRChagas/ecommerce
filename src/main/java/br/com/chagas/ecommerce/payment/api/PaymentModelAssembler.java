package br.com.chagas.ecommerce.payment.api;

import br.com.chagas.ecommerce.payment.Payment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PaymentModelAssembler implements RepresentationModelAssembler<Payment, EntityModel<Payment>> {

    @Override
    public EntityModel<Payment> toModel(Payment entity) {
        return EntityModel.of(entity);
    }
}
