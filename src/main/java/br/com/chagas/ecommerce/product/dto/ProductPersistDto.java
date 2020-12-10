package br.com.chagas.ecommerce.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductPersistDto {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @NotEmpty
    private String barCode;

    @NotNull
    private BigDecimal unitPrice;

    @NotNull
    private Long manufacturerId;

    @Deprecated
    public ProductPersistDto() {
    }
}
