package br.com.chagas.ecommerce.delivery;

import br.com.chagas.ecommerce.util.JsonUtil;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class DeliveryApiTest {

    @Mock
    private DeliveryService deliveryService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /delivery/ - Sucess")
    void save() throws Exception {
        var delivery = this.getDeliveryDefault();
        Mockito.doReturn(delivery).when(deliveryService).save(Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders.post("/delivery")
                .contentType(MediaTypes.HAL_JSON_VALUE)
                .content(JsonUtil.toJson(delivery)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("$.mode", Is.is("in-store withdrawal")));
    }

    @Test
    @DisplayName("GET /delivery/1 - Sucess")
    void findOneSucess() throws Exception {
        var delivery = this.getDeliveryDefault();
        Mockito.doReturn(delivery).when(deliveryService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET / Delivery / - Sucess")
    void findAllSucess() throws Exception {
        var consumerList = deliveryService.findAll(PageRequest.of(1, 2));
        Mockito.when(deliveryService.findAll(PageRequest.of(1, 2))).thenReturn(consumerList);
        Mockito.doReturn(consumerList).when(deliveryService).findAll(PageRequest.of(1, 2));
        mockMvc.perform(MockMvcRequestBuilders.get("/delivery"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("_embedded.deliveries[0].mode", Is.is("Taxi")))
                .andExpect(jsonPath("_embedded.deliveries[1].mode", Is.is("in-store withdrawal")));
    }

    private Delivery getDeliveryDefault() {
        return new Delivery("in-store withdrawal");
    }
}
