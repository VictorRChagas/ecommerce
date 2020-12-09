package br.com.chagas.ecommerce.manufacturer.api;

import br.com.chagas.ecommerce.delivery.dto.DeliveryPersistDto;
import br.com.chagas.ecommerce.framework.CrudRestController;
import br.com.chagas.ecommerce.framework.CrudService;
import br.com.chagas.ecommerce.manufacturer.Manufacturer;
import br.com.chagas.ecommerce.manufacturer.ManufacturerService;
import br.com.chagas.ecommerce.manufacturer.dto.ManufacturerPersistDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController extends CrudRestController<Manufacturer, Long> {

    private final ManufacturerService service;
    private final ManufacturerModelAssembler manufacturerModelAssembler;

    public ManufacturerController(ManufacturerService service, ManufacturerModelAssembler manufacturerModelAssembler) {
        this.service = service;
        this.manufacturerModelAssembler = manufacturerModelAssembler;
    }

    @Override
    public CrudService<Manufacturer, Long> getService() {
        return service;
    }

    @Override
    public RepresentationModelAssembler<Manufacturer, EntityModel<Manufacturer>> getRepresentationModelAssembler() {
        return manufacturerModelAssembler;
    }

    public void save(ManufacturerPersistDto manufacturerPersistDto) {

    }
}
