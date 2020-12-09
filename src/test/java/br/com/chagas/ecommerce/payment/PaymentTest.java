package br.com.chagas.ecommerce.payment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

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
        var payment = new Payment(NAME, INSTALLMENTS);
        Assertions.assertNull(payment.getId());
        Assertions.assertEquals(NAME, payment.getMode());
        Assertions.assertEquals(INSTALLMENTS, payment.getInstallments());
    }

    @Test
    @DisplayName("throw exception for invalid mails")
    void thorwsIllegalArgumentExceptionIfEmailIsNotValid() {
        assertThrows(NullPointerException.class, () -> new Payment(NAME, null));
    }

    @Test
    @DisplayName("throw exception for invalid mails")
    void thorwsIllegalArgumentExceptionIfEmailIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Payment("", INSTALLMENTS));
    }
}
