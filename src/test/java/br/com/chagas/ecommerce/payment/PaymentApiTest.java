package br.com.chagas.ecommerce.payment;

import br.com.chagas.ecommerce.payment.api.PaymentController;
import br.com.chagas.ecommerce.payment.dto.PaymentPersistDto;
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
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class PaymentApiTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @Autowired
    private static MockMvc mockMvc;

    @Test
    @DisplayName("make sure save method in service is called")
    void saveMethodInServiceIsCalled() {
        var deliveryPersistDto = new PaymentPersistDto("Credit Card", 1L);
        paymentController.save(deliveryPersistDto);
        verify(paymentService).save(any(Payment.class));
    }

    @Test
    @DisplayName("make sure find all works")
    void findAllMethodInServiceIsCalled() {
        paymentController.findAll(anyInt(), anyInt());
        PageRequest of = PageRequest.of(anyInt(), anyInt());
        verify(paymentService).findAll(of);
    }

    @Test
    @DisplayName("make sure the find one method in service is called")
    void findByIdMethodInServiceIsCalled() {
        paymentController.findById(anyLong());
        verify(paymentService).findById(anyLong());
    }

    @Test
    @DisplayName("make sure the delete method is called")
    void deleteByIdMethodInServiceIsCalled() {
        paymentController.deleteById(anyLong());
        verify(paymentService).deleteById(anyLong());
    }

    @Test
    @DisplayName("GET /character/1 - Sucess")
    void findOneSucess() throws Exception {
        var delivery = this.getDeliveryDefault();
        Mockito.doReturn(delivery).when(paymentService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/payment/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /character/1 - NotFound")
    void findOneNotFound() throws Exception {
        Mockito.doReturn(null).when(paymentService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/payment/{id}", 1))
                .andExpect(status().isNotFound());
    }

    private Payment getDeliveryDefault() {
        return new Payment("Credit Card", 5L);
    }
}
