package br.com.chagas.ecommerce.product.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductPersistDto {

    @NotNull
    @NotEmpty
    private String descricao;

    @NotNull
    private BigDecimal valor;

    @Deprecated
    public ProductPersistDto() {
    }

    public ProductPersistDto(@NotNull String descricao, @NotNull BigDecimal valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "ProdutoPersistDto{" +
                "descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
