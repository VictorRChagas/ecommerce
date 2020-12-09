package br.com.chagas.ecommerce.payment;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Entity
@ToString(of = "id")
@Table(name = "PAYMENT")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Email
    @NotNull
    @Column(name = "EMAIL", nullable = false, scale = 10, precision = 2)
    private Long installments;

    @Deprecated
    public Payment() {
    }

    public Payment(Long id) {
        this.id = id;
    }
}
