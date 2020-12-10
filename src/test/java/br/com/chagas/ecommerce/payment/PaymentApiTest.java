package br.com.chagas.ecommerce.payment;

import br.com.chagas.ecommerce.payment.api.PaymentController;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class PaymentApiTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /delivery/ - Sucess")
    void save() throws Exception {
        var payment = this.getPaymentDefault();
        Mockito.doReturn(payment).when(paymentService).save(Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders.post("/payment")
                .contentType(MediaTypes.HAL_JSON_VALUE)
                .content(JsonUtil.toJson(payment)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("$.mode", Is.is("Credit Card")));
    }


    @Test
    @DisplayName("GET /payment/1 - Sucess")
    void findOneSucess() throws Exception {
        var payment = this.getPaymentDefault();
        Mockito.doReturn(payment).when(paymentService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/payment/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET / Payment / - Sucess")
    void findAllSucess() throws Exception {
        var consumerList = paymentService.findAll(PageRequest.of(1, 2));
        Mockito.when(paymentService.findAll(PageRequest.of(1, 2))).thenReturn(consumerList);
        Mockito.doReturn(consumerList).when(paymentService).findAll(PageRequest.of(1, 2));
        mockMvc.perform(MockMvcRequestBuilders.get("/payment"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("_embedded.payments[0].mode", Is.is("Credit Card")));
    }

    private Payment getPaymentDefault() {
        return new Payment("Credit Card", 50L, BigDecimal.valueOf(100), 55D);
    }
}
