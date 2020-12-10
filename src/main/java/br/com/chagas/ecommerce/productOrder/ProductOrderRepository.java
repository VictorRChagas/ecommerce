package br.com.chagas.ecommerce.productOrder;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

    List<ProductOrder> findAllByOrderId(Long idOrder);
}
