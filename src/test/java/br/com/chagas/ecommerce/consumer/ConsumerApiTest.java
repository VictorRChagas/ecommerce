package br.com.chagas.ecommerce.consumer;

import br.com.chagas.ecommerce.consumer.api.ConsumerController;
import br.com.chagas.ecommerce.consumer.api.ConsumerModelAssembler;
import br.com.chagas.ecommerce.consumer.dto.ConsumerPersistDto;
import br.com.chagas.ecommerce.delivery.Delivery;
import br.com.chagas.ecommerce.framework.CrudRestController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ConsumerApiTest {

    @InjectMocks
    private ConsumerController consumerController;

    @Mock
    private ConsumerService consumerService;

    @InjectMocks
    private ConsumerModelAssembler consumerModelAssembler;

    @Autowired
    private static MockMvc mockMvc;

    @Test
    @DisplayName("make sure save method in service is called")
    void saveMethodInServiceIsCalled() {
        var consumerPersistDto = new ConsumerPersistDto("Victor", "jvr2000@outlook.com", "46991203609");
        consumerController.save(consumerPersistDto);
        verify(consumerService).save(any(Consumer.class));
    }

    @Test
    @DisplayName("make sure find all works")
    void findAllMethodInServiceIsCalled() {

        PageRequest of = PageRequest.of(anyInt(), anyInt());
        verify(consumerService).findAll(of);
    }

    @Test
    @DisplayName("make sure the find one method in service is called")
    void findByIdMethodInServiceIsCalled() {
        consumerController.findById(anyLong());
        verify(consumerService).findById(anyLong());
    }

    @Test
    @DisplayName("make sure the delete method is called")
    void deleteByIdMethodInServiceIsCalled() {
        consumerController.deleteById(anyLong());
        verify(consumerService).deleteById(anyLong());
    }

//    @Test
//    @DisplayName("GET /character/1 - Sucess")
//    void findOneSucess() throws Exception {
//        var delivery = this.getDeliveryDefault();
//        Mockito.doReturn(delivery).when(consumerService).findById(1L);
//        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/{id}", 1))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("GET /character/1 - NotFound")
//    void findOneNotFound() throws Exception {
//        Mockito.doReturn(null).when(consumerService).findById(1L);
//        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/{id}", 1))
//                .andExpect(status().isNotFound());
//    }

    private Delivery getDeliveryDefault() {
        return new Delivery("in-store withdrawal");
    }
}
