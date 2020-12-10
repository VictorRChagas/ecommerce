package br.com.chagas.ecommerce.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

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

    /***
     * the key is the product id and the value is the amount of product
     */
    private Map<Long, Long> unitByProduct;

}
