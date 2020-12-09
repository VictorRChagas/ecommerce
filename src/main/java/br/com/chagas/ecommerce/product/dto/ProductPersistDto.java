package br.com.chagas.ecommerce.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ProductPersistDto {

    @NotNull
    @NotEmpty
    private String name;

    @Deprecated
    public ProductPersistDto() {
    }
}
