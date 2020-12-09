package br.com.chagas.ecommerce.order;

import br.com.chagas.ecommerce.consumer.Consumer;
import br.com.chagas.ecommerce.delivery.Delivery;
import br.com.chagas.ecommerce.order.dto.OrderPersistDto;
import br.com.chagas.ecommerce.payment.Payment;
import br.com.chagas.ecommerce.productOrder.ProductOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Table(name = "ORDERING")
@EqualsAndHashCode(of = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_CONSUMER", nullable = false)
    private Consumer consumer;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_PAYMENT", nullable = false)
    private Payment payment;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_DELIVERY", nullable = false)
    private Delivery delivery;

    @JsonIgnore
    @Column(name = "STATUS", nullable = false)
    private OrderStatus orderStatus;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    private Set<ProductOrder> productSet = new HashSet<>();

    @NotNull
    private transient String status;

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
//        this.productSet = orderPersistDto.getProductIds().stream()
//                .map(ProductOrder::new).collect(Collectors.toSet());
    }

    public void setProductSet(Set<ProductOrder> productSet) {
//        this.productSet = productSet;
    }

    public void setOrderStatus(@NotNull OrderStatus status) {
        this.orderStatus = Objects.requireNonNull(status, "Status mut no be null");
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
