package br.com.chagas.ecommerce.framework;

import br.com.chagas.ecommerce.product.api.ProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public abstract class CrudRestController<T, ID> {

    private final Logger LOGGER = LoggerFactory.getLogger(getClazz());

    public abstract CrudService<T, ID> getService();
    public abstract RepresentationModelAssembler<T, EntityModel<T>> getRepresentationModelAssembler();
    public abstract Class<?> getClazz();

    @GetMapping
    public CollectionModel<EntityModel<T>> findAll(
            @NonNull @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
            @NonNull @RequestParam(value = "page", defaultValue = "0", required = false) Integer page) {
        LOGGER.debug("Fetching all {}", getClazz().getName());
        var pageable = PageRequest.of(page, size);

        var objectList = getService().findAll(pageable)
                .stream().map(getRepresentationModelAssembler()::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(objectList, linkTo(methodOn(ProductController.class).findAll(size, page)).withSelfRel());
    }

    @GetMapping("{query}")
    public EntityModel<T> findById(@NonNull @PathVariable("id") ID id) {
        LOGGER.debug("Searching {} by id {}", getClazz().getName(), id);
        return getRepresentationModelAssembler().toModel(getService().findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@NonNull @PathVariable ID id) {
        return getService().deleteById(id);
    }
}
