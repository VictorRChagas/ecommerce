package br.com.chagas.ecommerce.payment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentTest {

    private static final String NAME = "Credit Card";
    private static final Long INSTALLMENTS = 1L;

    @Test
    void emptyConstructor() {
        Assertions.assertDoesNotThrow((ThrowingSupplier<Payment>) Payment::new);
    }

    @Test
    void createNewInstance() {
        var payment = getDefaultPayment();
        Assertions.assertNull(payment.getId());
        Assertions.assertEquals(NAME, payment.getMode());
        Assertions.assertEquals(INSTALLMENTS, payment.getInstallments());
    }

    private Payment getDefaultPayment() {
        return new Payment(NAME, INSTALLMENTS, BigDecimal.valueOf(100), 50D);
    }

    @Test
    @DisplayName("throw exception for invalid mails")
    void thorwsIllegalArgumentExceptionIfEmailIsNotValid() {
        assertThrows(NullPointerException.class, () -> {
            var defaultPayment = getDefaultPayment();
            defaultPayment.setAmount(null);
        });
    }

    @Test
    @DisplayName("throw exception for invalid mails")
    void thorwsIllegalArgumentExceptionIfEmailIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Payment("", INSTALLMENTS, BigDecimal.ONE, 0D));
    }
}
