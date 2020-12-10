package br.com.chagas.ecommerce.order;

import br.com.chagas.ecommerce.consumer.Consumer;
import br.com.chagas.ecommerce.delivery.Delivery;
import br.com.chagas.ecommerce.payment.Payment;
import br.com.chagas.ecommerce.productOrder.ProductOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<ProductOrder> productSet = new HashSet<>();

    @NotNull
    private transient String status;

    /***
     * default construtor necessary for hibernate
     */
    @Deprecated
    public Order() {
    }

    public Order(Consumer consumer, Payment payment, Delivery delivery) {
        this.consumer = consumer;
        this.payment = payment;
        this.delivery = delivery;
    }

    public void setProductSet(Set<ProductOrder> productSet) {
        this.productSet = productSet;
    }

    public void setOrderStatus(@NotNull OrderStatus status) {
        this.orderStatus = Objects.requireNonNull(status, "Status mut no be null");
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = Objects.requireNonNull(consumer, "Consumer must not be null");
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = Objects.requireNonNull(delivery, "Delivery must not be null");
    }

    public void setPayment(Payment payment) {
        this.payment = Objects.requireNonNull(payment, "Payment must not be null");
    }
}
