package br.com.chagas.ecommerce.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderPersistDto {

    @NotNull
    private Long consumerId;

    @NotNull
    private Long paymentId;

    @NotNull
    private Long deliveryId;

    @NotNull
    private List<Long> productIds;

}
