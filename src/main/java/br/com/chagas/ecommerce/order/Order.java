package br.com.chagas.ecommerce.order;

import br.com.chagas.ecommerce.consumer.Consumer;
import br.com.chagas.ecommerce.delivery.Delivery;
import br.com.chagas.ecommerce.order.dto.OrderPersistDto;
import br.com.chagas.ecommerce.payment.Payment;
import br.com.chagas.ecommerce.productOrder.ProductOrder;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Table(name = "ORDER")
@EqualsAndHashCode(of = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_CONSUMER")
    private Consumer consumer;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_PAYMENT")
    private Payment payment;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_DELIVERY")
    private Delivery delivery;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<ProductOrder> productSet = new HashSet<>();

    /***
     * default construtor necessary for hibernate
     */
    @Deprecated
    public Order() {
    }

    public Order(OrderPersistDto orderPersistDto) {
        this.consumer = new Consumer(orderPersistDto.getConsumerId());
        this.delivery = new Delivery(orderPersistDto.getDeliveryId());
        this.payment = new Payment(orderPersistDto.getPaymentId());
        this.productSet = orderPersistDto.getProductIds().stream()
                .map(ProductOrder::new).collect(Collectors.toSet());
    }

    public void setProductSet(Set<ProductOrder> productSet) {
        this.productSet = productSet;
    }
}
