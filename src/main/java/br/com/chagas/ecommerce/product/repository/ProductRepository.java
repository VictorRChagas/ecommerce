package br.com.chagas.ecommerce.product.repository;

import br.com.chagas.ecommerce.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
