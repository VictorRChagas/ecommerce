package br.com.chagas.ecommerce.manufacturer.api;

import br.com.chagas.ecommerce.framework.CrudRestController;
import br.com.chagas.ecommerce.framework.CrudService;
import br.com.chagas.ecommerce.manufacturer.Manufacturer;
import br.com.chagas.ecommerce.manufacturer.ManufacturerService;
import br.com.chagas.ecommerce.manufacturer.dto.ManufacturerPersistDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController extends CrudRestController<Manufacturer, Long, ManufacturerPersistDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Manufacturer.class);
    private final ManufacturerService service;
    private final ManufacturerModelAssembler manufacturerModelAssembler;
    private final ModelMapper modelMapper;

    public ManufacturerController(ManufacturerService service, ManufacturerModelAssembler manufacturerModelAssembler, ModelMapper modelMapper) {
        this.service = service;
        this.manufacturerModelAssembler = manufacturerModelAssembler;
        this.modelMapper = modelMapper;
    }

    @Override
    public CrudService<Manufacturer, Long> getService() {
        return service;
    }

    @Override
    public RepresentationModelAssembler<Manufacturer, EntityModel<Manufacturer>> getRepresentationModelAssembler() {
        return manufacturerModelAssembler;
    }
}
