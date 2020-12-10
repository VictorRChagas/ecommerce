package br.com.chagas.ecommerce.product;

import br.com.chagas.ecommerce.product.api.ProductController;
import br.com.chagas.ecommerce.product.dto.ProductPersistDto;
import br.com.chagas.ecommerce.product.models.Product;
import br.com.chagas.ecommerce.product.models.ProductDetails;
import br.com.chagas.ecommerce.util.JsonUtil;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ProductApiTest {

    @MockBean
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /product/ - Sucess")
    void save() throws Exception {
        var returnProduct = this.getProductDefault();
        var productDto = this.getProductPersistDtoDefault();
        Mockito.doReturn(returnProduct).when(productController).save(Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaTypes.HAL_JSON_VALUE)
                .content(JsonUtil.toJson(productDto)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("$.name", Is.is("Cellphone")));
    }

    private ProductPersistDto getProductPersistDtoDefault() {
        return ProductPersistDto.builder()
                .name("Cellphone")
                .description("Samsung 8 GB RAM, 32 GM ROM")
                .amountStored(50L)
                .unitPrice(BigDecimal.valueOf(599))
                .build();
    }


    @Test
    @DisplayName("GET /product/1 - Sucess")
    void findOneSucess() throws Exception {
        var product = this.getProductDefault();
        Mockito.doReturn(product).when(productService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/product/{id}", 1))
                .andExpect(status().isOk());
    }

    private ResponseEntity<EntityModel<Product>> getProductDefault() throws Exception {
        return ResponseEntity.ok(EntityModel.of(new Product("Cellphone", getProductDetailsDefault())));
    }

    private ProductDetails getProductDetailsDefault() {
        return new ProductDetails("", "54651231546", BigDecimal.valueOf(599));
    }
}
