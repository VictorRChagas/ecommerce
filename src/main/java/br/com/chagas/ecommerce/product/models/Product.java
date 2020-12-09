package br.com.chagas.ecommerce.product.models;

import br.com.chagas.ecommerce.validator.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.persistence.*;
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


    @Deprecated
    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product(@NonNull String descricao, @NonNull ProductDetails productDetails) throws Exception {
        this.setName(descricao);
        this.setProductDetails(productDetails);
    }

    public void setName(@NonNull String descricao) throws Exception {
        this.name = Validator.ifNullOrEmptyThrows(descricao, new IllegalArgumentException("Description must not be null"));
    }

    public void setProductDetails(@NonNull ProductDetails productDetails) {
//        this.productDetails = Objects.requireNonNull(productDetails, "Value must not be null");
    }
}
