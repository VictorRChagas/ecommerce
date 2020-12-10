package br.com.chagas.ecommerce.manufacturer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManufacturerTest {

    private static final String MANUFACTURER = "NIKE";

    @Test
    void emptyConstructor() {
        Assertions.assertDoesNotThrow((ThrowingSupplier<Manufacturer>) Manufacturer::new);
    }

    @Test
    void createNewInstance() {
        var manufacturer = new Manufacturer(MANUFACTURER);
        Assertions.assertNull(manufacturer.getId());
        Assertions.assertEquals(MANUFACTURER, manufacturer.getName());
    }

    @Test
    @DisplayName("throw exception for invalid mails")
    void thorwsIllegalArgumentExceptionIfEmailIsNotValid() {
        assertThrows(IllegalArgumentException.class, () -> new Manufacturer((String) null));
    }

    @Test
    @DisplayName("throw exception for invalid mails")
    void thorwsIllegalArgumentExceptionIfEmailIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Manufacturer(""));
    }
}
