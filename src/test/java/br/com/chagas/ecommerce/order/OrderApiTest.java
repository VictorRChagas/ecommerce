package br.com.chagas.ecommerce.order;

import br.com.chagas.ecommerce.order.api.OrderController;
import br.com.chagas.ecommerce.order.dto.OrderPersistDto;
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

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class OrderApiTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Autowired
    private static MockMvc mockMvc;

    @Test
    @DisplayName("make sure save method in service is called")
    void saveMethodInServiceIsCalled() {
        orderController.save(this.getOrderPersistDto());
        verify(orderService).save(any(Order.class));
    }

    @Test
    @DisplayName("make sure find all works")
    void findAllMethodInServiceIsCalled() {
        orderController.findAll(anyInt(), anyInt());
        PageRequest of = PageRequest.of(anyInt(), anyInt());
        verify(orderService).findAll(of);
    }

    @Test
    @DisplayName("make sure the find one method in service is called")
    void findByIdMethodInServiceIsCalled() {
        orderController.findById(anyLong());
        verify(orderService).findById(anyLong());
    }

    @Test
    @DisplayName("make sure the delete method is called")
    void deleteByIdMethodInServiceIsCalled() {
        orderController.deleteById(anyLong());
        verify(orderService).deleteById(anyLong());
    }

    @Test
    @DisplayName("GET /character/1 - Sucess")
    void findOneSucess() throws Exception {
        var delivery = this.getDeliveryDefault();
        Mockito.doReturn(delivery).when(orderService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/order/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /character/1 - NotFound")
    void findOneNotFound() throws Exception {
        Mockito.doReturn(null).when(orderService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/order/{id}", 1))
                .andExpect(status().isNotFound());
    }

    private Order getDeliveryDefault() {
        return new Order(this.getOrderPersistDto());
    }

    private OrderPersistDto getOrderPersistDto() {
        return new OrderPersistDto(1L,1L,1L, Collections.emptyList());
    }
}
