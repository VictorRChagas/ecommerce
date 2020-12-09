package br.com.chagas.ecommerce.product.api;

import br.com.chagas.ecommerce.framework.CrudRestController;
import br.com.chagas.ecommerce.framework.CrudService;
import br.com.chagas.ecommerce.product.ProductService;
import br.com.chagas.ecommerce.product.dto.ProductPersistDto;
import br.com.chagas.ecommerce.product.dto.ProductUpdateDto;
import br.com.chagas.ecommerce.product.models.Product;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController extends CrudRestController<Product, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;
    private final ProductModelAssembler productModelAssembler;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ProductModelAssembler productModelAssembler, ModelMapper modelMapper) {
        this.productService = productService;
        this.productModelAssembler = productModelAssembler;
        this.modelMapper = modelMapper;
    }

    @Override
    public CrudService<Product, Long> getService() {
        return productService;
    }

    @Override
    public RepresentationModelAssembler<Product, EntityModel<Product>> getRepresentationModelAssembler() {
        return productModelAssembler;
    }

    @Override
    public Class<?> getClazz() {
        return this.getClass();
    }

    @PostMapping
    public ResponseEntity<EntityModel<Product>> save(@NonNull @Valid @RequestBody ProductPersistDto productPersist) {
        LOGGER.debug("Saving new Product");
        var product = modelMapper.map(productPersist, Product.class);
        var entityModel = productModelAssembler.toModel(productService.save(product));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Product>> updateById(
            @NonNull @Valid @RequestBody ProductPersistDto productPersist,
            @NonNull @PathVariable Long id) {
        LOGGER.debug("Updating product id: {}", id);
        var product = productService.findById(id);
        modelMapper.map(productPersist, product);
        var entityModel = productModelAssembler.toModel(productService.save(product));

        return ResponseEntity.ok(entityModel);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateProductById(@NonNull @PathVariable("id") Long id,
                                               @Valid @RequestBody ProductUpdateDto produtoUpdateDto) {
        LOGGER.debug("Updating product id: {}", id);
        var product = productService.findById(id);
        var productUpdated = productService.update(produtoUpdateDto, product);
        var entityModel = productModelAssembler.toModel(productUpdated);
        return ResponseEntity.ok(entityModel);
    }
}
