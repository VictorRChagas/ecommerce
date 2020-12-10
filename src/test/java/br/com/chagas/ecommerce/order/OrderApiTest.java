package br.com.chagas.ecommerce.order;

import br.com.chagas.ecommerce.consumer.Consumer;
import br.com.chagas.ecommerce.delivery.Delivery;
import br.com.chagas.ecommerce.order.api.OrderController;
import br.com.chagas.ecommerce.order.dto.OrderPersistDto;
import br.com.chagas.ecommerce.payment.Payment;
import br.com.chagas.ecommerce.product.models.Product;
import br.com.chagas.ecommerce.productOrder.ProductOrder;
import br.com.chagas.ecommerce.util.JsonUtil;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class OrderApiTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /order/ - Sucess")
    void save() throws Exception {
        var orderPersistDto = this.getOrderPersistDto();
        var orderDefault = this.getOrderDefault();
        Mockito.doReturn(orderDefault).when(orderService).save(Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders.post("/order")
                .contentType(MediaTypes.HAL_JSON_VALUE)
                .content(JsonUtil.toJson(orderPersistDto)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("$.consumer.name", Is.is("Victor Chagas")))
                .andExpect(jsonPath("payment.mode", Is.is("Credit Card")));
    }

    private Order getOrderDefault() {
        var order = new Order();
        order.setPayment(new Payment(1L));
        order.setDelivery(new Delivery(1L, "in-store withdrawal"));
        order.setConsumer(new Consumer(1L));
        order.setProductSet(Set.of(new ProductOrder(new Product(1L), order, 1L)));
        return order;
    }

    private OrderPersistDto getOrderPersistDto() {
        return new OrderPersistDto(1L, 1L, 1L, List.of(1L), Map.of(1L, 1L));
    }
}
