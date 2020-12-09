package br.com.chagas.ecommerce.product;

import br.com.chagas.ecommerce.framework.CrudServiceImpl;
import br.com.chagas.ecommerce.product.dto.ProductUpdateDto;
import br.com.chagas.ecommerce.product.models.Product;
import br.com.chagas.ecommerce.product.repository.ProductRepository;
import br.com.chagas.ecommerce.util.JsonUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends CrudServiceImpl<Product, Long> implements ProductService {

    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Product, Long> getRepository() {
        return repository;
    }

    @Override
    public Product update(ProductUpdateDto productUpdateDto, Product product) {
        JsonUtil.changeIfPresent(productUpdateDto.getDescricao(), product::setName);
        JsonUtil.changeIfPresent(productUpdateDto.getValor(), product::setValor);
        return repository.save(product);
    }
}
