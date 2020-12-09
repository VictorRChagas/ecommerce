package br.com.chagas.ecommerce.product;

import br.com.chagas.ecommerce.product.api.ProductController;
import br.com.chagas.ecommerce.product.dto.ProductPersistDto;
import br.com.chagas.ecommerce.product.models.Product;
import br.com.chagas.ecommerce.product.models.ProductDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class ProductApiTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Autowired
    private static MockMvc mockMvc;

    @Test
    @DisplayName("make sure save method in service is called")
    void saveMethodInServiceIsCalled() {
        var productPersistDto = new ProductPersistDto("Computer", BigDecimal.valueOf(899));
        productController.save(productPersistDto);
        verify(productService).save(any(Product.class));
    }

    @Test
    @DisplayName("make sure find all works")
    void findAllMethodInServiceIsCalled() {
        productController.findAll(anyInt(), anyInt());
        PageRequest of = PageRequest.of(anyInt(), anyInt());
        verify(productService).findAll(of);
    }

    @Test
    @DisplayName("make sure the find one method in service is called")
    void findByIdMethodInServiceIsCalled() {
        productController.findById(anyLong());
        verify(productService).findById(anyLong());
    }

    @Test
    @DisplayName("make sure the delete method is called")
    void deleteByIdMethodInServiceIsCalled() {
        productController.deleteById(anyLong());
        verify(productService).deleteById(anyLong());
    }

    @Test
    @DisplayName("GET Find By Id - Sucess")
    void findOneSucess() throws Exception {
        var delivery = this.getProductDefault();
        Mockito.doReturn(delivery).when(productService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/product/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET Find By Id - NotFound")
    void findOneNotFound() throws Exception {
        Mockito.doReturn(null).when(productService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/product/{id}", 1))
                .andExpect(status().isNotFound());
    }

    private Product getProductDefault() throws Exception {
        return new Product("Computer",
                new ProductDetails("HP Computer", "54789245", BigDecimal.valueOf(899)));
    }
}
