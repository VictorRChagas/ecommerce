package br.com.chagas.ecommerce.product;

import br.com.chagas.ecommerce.product.models.Product;
import br.com.chagas.ecommerce.product.models.ProductDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    private static final String NAME = "Apple";
    private static final ProductDetails PRODUCT_DETAILS = new ProductDetails("Apple", "547896320", BigDecimal.ONE);

    @Test
    void emptyConstructor() {
        Assertions.assertDoesNotThrow((ThrowingSupplier<Product>) Product::new);
    }

    @Test
    void createNewInstance() throws Exception {
        var product = new Product(NAME, PRODUCT_DETAILS);
        Assertions.assertNull(product.getId());
        Assertions.assertEquals(product.getName(), NAME);
        Assertions.assertEquals(product.getProductDetails(), PRODUCT_DETAILS);
    }

    @Test
    @DisplayName("throw exception for invalid mails")
    void thorwsIllegalArgumentExceptionIfEmailIsNotValid() {
        assertThrows(IllegalArgumentException.class, () -> new Product("", PRODUCT_DETAILS));
    }

    @Test
    @DisplayName("throw exception for invalid mails")
    void thorwsIllegalArgumentExceptionIfEmailIsEmpty() {
        assertThrows(NullPointerException.class, () -> new Product(NAME, null));
    }
}
