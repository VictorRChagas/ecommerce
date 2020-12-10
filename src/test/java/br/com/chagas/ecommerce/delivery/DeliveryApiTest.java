package br.com.chagas.ecommerce.delivery;

import br.com.chagas.ecommerce.delivery.api.DeliveryController;
import br.com.chagas.ecommerce.delivery.api.DeliveryModelAssembler;
import br.com.chagas.ecommerce.delivery.dto.DeliveryPersistDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class DeliveryApiTest {

    @InjectMocks
    private DeliveryController deliveryController;

    @Mock
    private DeliveryService deliveryService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private DeliveryModelAssembler deliveryModelAssembler;

    @Autowired
    private static MockMvc mockMvc;

    @Test
    @DisplayName("make sure save method in service is called")
    void saveMethodInServiceIsCalled() {
        var deliveryPersistDto = new DeliveryPersistDto("in-store withdrawal");

        when(deliveryModelAssembler.toModel(any()))
                .thenReturn(EntityModel.of(getDeliveryDefault()));

        when(deliveryService.save(any()))
                .thenReturn(getDeliveryDefault());

        deliveryController.save(deliveryPersistDto);
        verify(deliveryService).save(any());
    }

    @Test
    @DisplayName("make sure find all works")
    void findAllMethodInServiceIsCalled() {
        deliveryController.findAll(anyInt(), anyInt());
        PageRequest of = PageRequest.of(anyInt(), anyInt());
        verify(deliveryService).findAll(of);
    }

    @Test
    @DisplayName("make sure the find one method in service is called")
    void findByIdMethodInServiceIsCalled() {
        deliveryController.findById(anyLong());
        verify(deliveryService).findById(anyLong());
    }

    @Test
    @DisplayName("make sure the delete method is called")
    void deleteByIdMethodInServiceIsCalled() {
        deliveryController.deleteById(anyLong());
        verify(deliveryService).deleteById(anyLong());
    }

//    @Test
//    @DisplayName("GET /character/1 - Sucess")
//    void findOneSucess() throws Exception {
//        var delivery = this.getDeliveryDefault();
//        Mockito.doReturn(delivery).when(deliveryService).findById(1L);
//        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/{id}", 1))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("GET /character/1 - NotFound")
//    void findOneNotFound() throws Exception {
//        Mockito.doReturn(null).when(deliveryService).findById(1L);
//        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/{id}", 1))
//                .andExpect(status().isNotFound());
//    }

    private Delivery getDeliveryDefault() {
        return new Delivery("in-store withdrawal");
    }
}
