package br.com.chagas.ecommerce.product;

import br.com.chagas.ecommerce.framework.CrudService;
import br.com.chagas.ecommerce.product.dto.ProductUpdateDto;
import br.com.chagas.ecommerce.product.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

public interface ProductService extends CrudService<Product, Long> {

    Product update(ProductUpdateDto produtoUpdateDto, Product product);
}
