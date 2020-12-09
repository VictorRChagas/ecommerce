package br.com.chagas.ecommerce.delivery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeliveryTest {

    private static final String MODE = "in-store withdrawal";

    @Test
    void emptyConstructor() {
        Assertions.assertDoesNotThrow(((ThrowingSupplier<Delivery>) Delivery::new));
    }

    @Test
    void createNewInstance() {
        var delivery = new Delivery(MODE);
        Assertions.assertNull(delivery.getId());
        Assertions.assertEquals(MODE, delivery.getMode());
    }

    @Test
    @DisplayName("throw exception for invalid mails")
    void thorwsIllegalArgumentExceptionIfEmailIsNotValid() {
        assertThrows(IllegalArgumentException.class, () -> new Delivery(""));
    }

    @Test
    @DisplayName("throw exception for invalid mails")
    void thorwsIllegalArgumentExceptionIfEmailIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Delivery((String) null));
    }
}
