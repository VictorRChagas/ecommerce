package br.com.chagas.ecommerce.consumer;

import br.com.chagas.ecommerce.consumer.api.ConsumerController;
import br.com.chagas.ecommerce.consumer.dto.ConsumerPersistDto;
import br.com.chagas.ecommerce.delivery.Delivery;
import br.com.chagas.ecommerce.manufacturer.Manufacturer;
import br.com.chagas.ecommerce.manufacturer.ManufacturerService;
import br.com.chagas.ecommerce.manufacturer.api.ManufacturerController;
import br.com.chagas.ecommerce.manufacturer.dto.ManufacturerPersistDto;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class ConsumerApiTest {

    @InjectMocks
    private ConsumerController consumerController;

    @Mock
    private ConsumerService jconsumerService;

    @Autowired
    private static MockMvc mockMvc;

    @Test
    @DisplayName("make sure save method in service is called")
    void saveMethodInServiceIsCalled() {
        var consumerPersistDto = new ConsumerPersistDto("Victor", "jvr2000@outlook.com");
        consumerController.save(consumerPersistDto);
        verify(jconsumerService).save(any(Consumer.class));
    }

    @Test
    @DisplayName("make sure find all works")
    void findAllMethodInServiceIsCalled() {
        consumerController.findAll(anyInt(), anyInt());
        PageRequest of = PageRequest.of(anyInt(), anyInt());
        verify(jconsumerService).findAll(of);
    }

    @Test
    @DisplayName("make sure the find one method in service is called")
    void findByIdMethodInServiceIsCalled() {
        consumerController.findById(anyLong());
        verify(jconsumerService).findById(anyLong());
    }

    @Test
    @DisplayName("make sure the delete method is called")
    void deleteByIdMethodInServiceIsCalled() {
        consumerController.deleteById(anyLong());
        verify(jconsumerService).deleteById(anyLong());
    }

    @Test
    @DisplayName("GET /character/1 - Sucess")
    void findOneSucess() throws Exception {
        var delivery = this.getDeliveryDefault();
        Mockito.doReturn(delivery).when(jconsumerService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /character/1 - NotFound")
    void findOneNotFound() throws Exception {
        Mockito.doReturn(null).when(jconsumerService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/{id}", 1))
                .andExpect(status().isNotFound());
    }

    private Delivery getDeliveryDefault() {
        return new Delivery("in-store withdrawal");
    }
}
