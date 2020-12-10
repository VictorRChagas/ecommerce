package br.com.chagas.ecommerce.product;

import br.com.chagas.ecommerce.framework.CrudServiceImpl;
import br.com.chagas.ecommerce.manufacturer.ManufacturerService;
import br.com.chagas.ecommerce.product.dto.ProductUpdateDto;
import br.com.chagas.ecommerce.product.models.Product;
import br.com.chagas.ecommerce.product.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends CrudServiceImpl<Product, Long> implements ProductService {

    private final ProductRepository repository;
    private final ManufacturerService manufacturerService;

    public ProductServiceImpl(ProductRepository repository, ManufacturerService manufacturerService) {
        this.repository = repository;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public JpaRepository<Product, Long> getRepository() {
        return repository;
    }

    @Override
    public Product update(ProductUpdateDto productUpdateDto, Product product) {
//        JsonUtil.changeIfPresent(productUpdateDto.getDescricao(), product::setName);
        return repository.save(product);
    }

    @Override
    protected void postSave(Product entity) {
        var manufacturerId = entity.getManufacturer().getId();
        entity.setManufacturer(manufacturerService.findById(manufacturerId));
    }

    @Override
    public List<Product> findAllByIdList(Iterable<Long> idList) {
        return repository.findAllById(idList);
    }
}
