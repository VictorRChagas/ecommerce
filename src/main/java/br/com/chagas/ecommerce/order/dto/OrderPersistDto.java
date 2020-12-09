package br.com.chagas.ecommerce.order.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class OrderPersistDto {

    @NotNull
    private Long consumerId;

    @NotNull
    private Long paymentId;

    @NotNull
    private Long deliveryId;

    private List<Long> productIds;

}
