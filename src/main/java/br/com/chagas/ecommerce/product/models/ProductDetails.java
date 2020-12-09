package br.com.chagas.ecommerce.product.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT")
@Data
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPTION", nullable = false, length = 100)
    private String description;

    @Column(name = "BAR_CODE", nullable = false, scale = 10, precision = 2)
    private String barCode;

    @Column(name = "UNIT_PRICE", nullable = false, scale = 10, precision = 2)
    private BigDecimal unitPrice;
}
