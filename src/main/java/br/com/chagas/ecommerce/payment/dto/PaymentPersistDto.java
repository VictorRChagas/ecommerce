package br.com.chagas.ecommerce.payment.dto;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@ToString(of = "mode")
public class PaymentPersistDto {

    @NotNull
    @NotEmpty
    private String mode;

    @NotNull
    private Long installments;

    public PaymentPersistDto(@NotNull @NotEmpty String mode, @NotNull Long installments) {
        this.mode = mode;
        this.installments = installments;
    }
}
