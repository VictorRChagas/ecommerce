package br.com.chagas.ecommerce.consumer;

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
public class ConsumerApiTest {

    @Mock
    private ConsumerService consumerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /delivery/1 - Sucess")
    void findOneSucess() throws Exception {
        var consumer = this.getConsumerDefault();
        Mockito.doReturn(consumer).when(consumerService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /consumer - NotFound")
    void findOneNotFound() throws Exception {
        Mockito.doReturn(null).when(consumerService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/consumer/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /Consumer/ - Sucess")
    void findAllSucess() throws Exception {
        var consumerList = consumerService.findAll(PageRequest.of(1, 2));
        Mockito.when(consumerService.findAll(PageRequest.of(1, 2))).thenReturn(consumerList);
        Mockito.doReturn(consumerList).when(consumerService).findAll(PageRequest.of(1, 2));
        mockMvc.perform(MockMvcRequestBuilders.get("/consumer"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("_embedded.consumers[0].name", Is.is("Victor Chagas")))
                .andExpect(jsonPath("_embedded.consumers[1].name", Is.is("Victor")));
    }

    private Consumer getConsumerDefault() {
        return new Consumer("João Chagas", "joao@outlook.com", "82991202580");
    }
}
