package br.com.chagas.ecommerce.product.dto;

import org.openapitools.jackson.nullable.JsonNullable;

import java.math.BigDecimal;

public class ProductUpdateDto {

    private final JsonNullable<String> descricao = JsonNullable.undefined();
    private final JsonNullable<BigDecimal> valor = JsonNullable.undefined();

    @Deprecated
    public ProductUpdateDto() {
    }

    public JsonNullable<String> getDescricao() {
        return descricao;
    }

    public JsonNullable<BigDecimal> getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "ProdutoUpdateDto{" +
                "descricao=" + descricao +
                ", valor=" + valor +
                '}';
    }
}
