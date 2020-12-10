package br.com.chagas.ecommerce.productOrder;

import br.com.chagas.ecommerce.order.Order;
import br.com.chagas.ecommerce.product.models.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUCT_ORDER")
@Data
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ID_ORDER", nullable = false)
    private Order order;

    @NotNull
    @OneToOne
    @JoinColumn(name = "ID_PRODUCT")
    private Product product;

    /***
     * default construtor necessary for hibernate
     */
    @Deprecated
    public ProductOrder() {
    }

    public ProductOrder(@NotNull Product product, @NotNull Order order) {
        this.product = product;
        this.order = order;
    }
}
