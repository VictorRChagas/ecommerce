package br.com.chagas.ecommerce.product.models;

import br.com.chagas.ecommerce.product.dto.ProductPersistDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Entity
@Table(name = "PRODUCT_DETAILS")
@EqualsAndHashCode(of = "id")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @JsonBackReference
    @OneToOne(optional = false)
    @JoinColumn(name = "ID_PRODUCT", unique = true)
    private Product product;

    @Column(name = "DESCRIPTION", nullable = false, length = 100)
    private String description;

    @Column(name = "BAR_CODE", nullable = false, scale = 10, precision = 2)
    private String barCode;

    @Column(name = "UNIT_PRICE", nullable = false, scale = 10, precision = 2)
    private BigDecimal unitPrice;

    @Deprecated
    public ProductDetails() {
    }

    public ProductDetails(@NotNull String description, @NotNull String barCode, @NotNull BigDecimal unitPrice) {
        this.setDescription(description);
        this.setBarCode(barCode);
        this.setUnitPrice(unitPrice);
    }

    public ProductDetails(ProductPersistDto productPersistDto) {
        this.setDescription(productPersistDto.getDescription());
        this.setBarCode(productPersistDto.getBarCode());
        this.setUnitPrice(productPersistDto.getUnitPrice());
    }

    public void setDescription(@NotEmpty @NotNull String description) {
        this.description = Objects.requireNonNull(description, "Description of the details must not be null");
    }

    public void setBarCode(@NotEmpty @NotNull String barCode) {
        this.barCode = Objects.requireNonNull(barCode, "Barcode must not be null");
    }

    public void setUnitPrice(@NotNull BigDecimal unitPrice) {
        this.unitPrice = Objects.requireNonNull(unitPrice, "Unitprice must not be null");
    }

    public void setProduct(@NotNull Product product) {
        this.product = Objects.requireNonNull(product, "Product must not be null");
    }
}
