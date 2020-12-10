package br.com.chagas.ecommerce.manufacturer;

import br.com.chagas.ecommerce.manufacturer.api.ManufacturerController;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ManufacturerApiTest {

    @InjectMocks
    private ManufacturerController manufacturerController;

    @Mock
    private ManufacturerService manufacturerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /delivery/ - Sucess")
    void save() throws Exception {
        var manufacturer = this.getManufacturerDefault();
        Mockito.doReturn(manufacturer).when(manufacturerService).save(Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders.post("/manufacturer")
                .contentType(MediaTypes.HAL_JSON_VALUE)
                .content(JsonUtil.toJson(manufacturer)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("$.name", Is.is("Netshoes")));
    }

    @Test
    @DisplayName("GET /manufacturer/1 - Sucess")
    void findOneSucess() throws Exception {
        var manufacturer = this.getManufacturerDefault();
        Mockito.doReturn(manufacturer).when(manufacturerService).findById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/manufacturer/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET / Manufacturer / - Sucess")
    void findAllSucess() throws Exception {
        var consumerList = manufacturerService.findAll(PageRequest.of(1, 2));
        Mockito.when(manufacturerService.findAll(PageRequest.of(1, 2))).thenReturn(consumerList);
        Mockito.doReturn(consumerList).when(manufacturerService).findAll(PageRequest.of(1, 2));
        mockMvc.perform(MockMvcRequestBuilders.get("/manufacturer"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("_embedded.manufacturers[0].name", Is.is("Nike")))
                .andExpect(jsonPath("_embedded.manufacturers[1].name", Is.is("Netshoes")));
    }

    private Manufacturer getManufacturerDefault() {
        return new Manufacturer("Netshoes");
    }
}
