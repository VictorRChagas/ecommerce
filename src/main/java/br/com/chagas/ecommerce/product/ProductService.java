package br.com.chagas.ecommerce.product;

import br.com.chagas.ecommerce.framework.CrudService;
import br.com.chagas.ecommerce.product.dto.ProductUpdateDto;
import br.com.chagas.ecommerce.product.models.Product;

import java.util.List;

public interface ProductService extends CrudService<Product, Long> {

    Product update(ProductUpdateDto produtoUpdateDto, Product product);

    List<Product> findAllByIdList(Iterable<Long> idList);

}
