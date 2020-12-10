package br.com.chagas.ecommerce.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@ToString(of = "mode")
public class PaymentPersistDto {

    @NotNull
    @NotEmpty
    private String mode;

    @NotNull
    private Long installments;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Double installmentValue;
}
