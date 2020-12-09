package br.com.chagas.ecommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Transactional
    @Modifying
    @Query("update Order o set o.orderStatus = 'APPROVED' where o.id =  ?1")
    int approveOrder(Long id);

    @Transactional
    @Modifying
    @Query("update Order o set o.orderStatus = 'CANCELED' where o.id =  ?1")
    int cancelOrder(Long orderId);
}
