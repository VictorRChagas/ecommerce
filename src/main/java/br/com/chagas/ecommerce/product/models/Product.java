package br.com.chagas.ecommerce.product.models;

import br.com.chagas.ecommerce.manufacturer.Manufacturer;
import br.com.chagas.ecommerce.product.dto.ProductPersistDto;
import br.com.chagas.ecommerce.validator.Validator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Entity
@ToString(of = "id")
@Table(name = "PRODUCT")
@EqualsAndHashCode(of = "id")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;
    
    @JsonManagedReference
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductDetails productDetails;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_MANUFACTURER", nullable = false)
    private Manufacturer manufacturer;

    @Column(name = "AMOUNT", nullable = false)
    private Long amountStored;

    @Deprecated
    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product(ProductPersistDto productPersistDto) throws Exception {
        this.productDetails = new ProductDetails(productPersistDto);
        this.manufacturer = new Manufacturer(productPersistDto.getManufacturerId());
        this.productDetails.setProduct(this);
        this.setName(productPersistDto.getName());
        this.setAmountStored(productPersistDto.getAmountStored());
    }

    public Product(@NonNull String descricao, @NonNull ProductDetails productDetails) throws Exception {
        this.setName(descricao);
        this.setProductDetails(productDetails);
    }

    public void setName(@NonNull String descricao) throws Exception {
        this.name = Validator.ifNullOrEmptyThrows(descricao, new IllegalArgumentException("Description must not be null"));
    }

    public void setProductDetails(@NonNull ProductDetails productDetails) {
        this.productDetails = Objects.requireNonNull(productDetails, "Value must not be null");
    }

    public void setManufacturer(@NotNull Manufacturer manufacturer) {
        this.manufacturer = Objects.requireNonNull(manufacturer, "Value must not be null");
    }

    public void setAmountStored(Long amountStored) {
        this.amountStored = Objects.requireNonNull(amountStored, "Amount must not be null");
    }
}
