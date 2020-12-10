package br.com.chagas.ecommerce.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsumerTest {

    private static final String NAME = "João Victor";
    private static final String EMAIL = "jvrc2000@outlook.com";
    private static final String PHONE = "5546991203609";

    @Test
    void emptyConstructor() {
        Assertions.assertDoesNotThrow((ThrowingSupplier<Consumer>) Consumer::new);
    }

    @Test
    void createNewInstance() {
        var consumer = new Consumer(NAME, EMAIL, PHONE);
        Assertions.assertNull(consumer.getId());
        Assertions.assertEquals(EMAIL, consumer.getEmail());
    }

    @Test
    @DisplayName("throw exception for invalid mails")
    void thorwsIllegalArgumentExceptionIfEmailIsNotValid() {
        assertThrows(IllegalArgumentException.class, () -> new Consumer(NAME, "test.com", null));
    }

    @Test
    @DisplayName("throw exception for invalid mails")
    void thorwsIllegalArgumentExceptionIfEmailIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Consumer(NAME, "", PHONE));
    }
}
