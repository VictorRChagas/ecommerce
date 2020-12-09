package br.com.chagas.ecommerce.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderTest {

//    private static final String NAME = "João Victor";
//    private static final String EMAIL = "jvrc2000@outlook.com";
//
//    @Test
//    void emptyConstructor() {
//        Assertions.assertDoesNotThrow((ThrowingSupplier<Order>) Order::new);
//    }
//
//    @Test
//    void createNewInstance() {
//        var consumer = new Order(NAME, EMAIL);
//        Assertions.assertNull(consumer.getId());
//        Assertions.assertEquals(EMAIL, consumer.getEmail());
//    }
//
//    @Test
//    @DisplayName("throw exception for invalid mails")
//    void thorwsIllegalArgumentExceptionIfEmailIsNotValid() {
//        assertThrows(IllegalArgumentException.class, () -> new Order(NAME, "test.com"));
//    }
//
//    @Test
//    @DisplayName("throw exception for invalid mails")
//    void thorwsIllegalArgumentExceptionIfEmailIsEmpty() {
//        assertThrows(IllegalArgumentException.class, () -> new Order(NAME, ""));
//    }
}
