package br.com.chagas.ecommerce.product.models;

import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@ToString(of = "id")
@Table(name = "PRODUCT")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRICAO", nullable = false, length = 100)
    private String name;

    @Column(name = "VALOR", nullable = false, scale = 10, precision = 2)
    private BigDecimal valor;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private ProductDetails productDetails;

    @Deprecated
    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    private Product(@NonNull String descricao, @NonNull BigDecimal valor) {
        setName(descricao);
        setValor(valor);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setName(@NonNull String descricao) {
        this.name = Objects.requireNonNull(descricao, "Description must not be null");
    }

    public void setValor(@NonNull BigDecimal valor) {
        this.valor = Objects.requireNonNull(valor, "Value must not be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
