package br.com.chagas.ecommerce.payment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Entity
@ToString(of = "id")
@Table(name = "PAYMENT")
@EqualsAndHashCode(of = "id")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "MODE", nullable = false, length = 100)
    private String mode;

    @NotNull
    @Column(name = "INSTALLMENTS", nullable = false, scale = 10, precision = 2)
    private Long installments;

    @NotNull
    @Column(name = "AMOUNT", nullable = false, scale = 10, precision = 2)
    private BigDecimal amount;

    @NotNull
    @Column(name = "INSTALLMENT_VALUE", nullable = false, scale = 10, precision = 2)
    private Double installmentValue;

    @Deprecated
    public Payment() {
    }

    public Payment(Long id) {
        this.id = id;
    }

    public Payment(@NotNull String mode, @NotNull Long installments,
                   @NotNull BigDecimal amount, @NotNull Double installmentValue) {
        this.setMode(mode);
        this.setInstallments(installments);
        this.setAmount(amount);
        this.setInstallmentValue(installmentValue);
    }

    public void setMode(String mode) {
        if (Objects.isNull(mode) || mode.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        this.mode = mode;
    }

    public void setInstallments(@NotNull Long installments) {
        this.installments = Objects.requireNonNull(installments, "Installments must not be null");
    }

    public void setAmount(@NotNull BigDecimal amount) {
        this.amount = Objects.requireNonNull(amount, "Amount must not be null");
    }

    public void setInstallmentValue(@NotNull Double installmentValue) {
        this.installmentValue = Objects.requireNonNull(installmentValue, "Value of installments must not be null");
    }
}
