package br.com.chagas.ecommerce.manufacturer;

import br.com.chagas.ecommerce.delivery.Delivery;
import br.com.chagas.ecommerce.manufacturer.api.ManufacturerController;
import br.com.chagas.ecommerce.manufacturer.dto.ManufacturerPersistDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ManufacturerApiTest {

    @InjectMocks
    private ManufacturerController manufacturerController;

    @Mock
    private ManufacturerService manufacturerService;

    @InjectMocks
    private ModelMapper modelMapper;

    @Autowired
    private static MockMvc mockMvc;

    @Test
    @DisplayName("make sure save method in service is called")
    void saveMethodInServiceIsCalled() {
        var manufacturerPersistDto = new ManufacturerPersistDto("in-store withdrawal");
        when(modelMapper.map(any(), any())).thenReturn(new Manufacturer("in-store withdrawal"));
        manufacturerController.save(manufacturerPersistDto);
        verify(manufacturerService).save(any());
    }

    @Test
    @DisplayName("make sure find all works")
    void findAllMethodInServiceIsCalled() {
        manufacturerController.findAll(anyInt(), anyInt());
        PageRequest of = PageRequest.of(anyInt(), anyInt());
        verify(manufacturerService).findAll(of);
    }

    @Test
    @DisplayName("make sure the find one method in service is called")
    void findByIdMethodInServiceIsCalled() {
        manufacturerController.findById(anyLong());
        verify(manufacturerService).findById(anyLong());
    }

    @Test
    @DisplayName("make sure the delete method is called")
    void deleteByIdMethodInServiceIsCalled() {
        manufacturerController.deleteById(anyLong());
        verify(manufacturerService).deleteById(anyLong());
    }

//    @Test
//    @DisplayName("GET /character/1 - Sucess")
//    void findOneSucess() throws Exception {
//        var delivery = this.getDeliveryDefault();
//        Mockito.doReturn(delivery).when(manufacturerService).findById(1L);
//        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/{id}", 1))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("GET /character/1 - NotFound")
//    void findOneNotFound() throws Exception {
//        Mockito.doReturn(null).when(manufacturerService).findById(1L);
//        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/{id}", 1))
//                .andExpect(status().isNotFound());
//    }

    private Delivery getDeliveryDefault() {
        return new Delivery("in-store withdrawal");
    }
}
