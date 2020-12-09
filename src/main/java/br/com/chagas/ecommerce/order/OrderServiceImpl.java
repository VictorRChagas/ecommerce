package br.com.chagas.ecommerce.order;

import br.com.chagas.ecommerce.framework.CrudServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderServiceImpl extends CrudServiceImpl<Order, Long> implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Order, Long> getRepository() {
        return repository;
    }

    @Override
    public Order save(Order entity) {

        return super.save(entity);
    }
}
