package br.com.chagas.ecommerce.delivery.dto;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString(of = "mode")
public class DeliveryPersistDto {

    @NotNull
    private String mode;

    @Deprecated
    public DeliveryPersistDto() {
    }

    public DeliveryPersistDto(@NotNull String mode) {
        this.mode = mode;
    }
}
