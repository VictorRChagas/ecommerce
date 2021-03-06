package br.com.chagas.ecommerce.order;

import br.com.chagas.ecommerce.framework.CrudServiceImpl;
import br.com.chagas.ecommerce.order.events.PreSaveOrderEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends CrudServiceImpl<Order, Long> implements OrderService {

    private final OrderRepository repository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderServiceImpl(OrderRepository repository, ApplicationEventPublisher applicationEventPublisher) {
        this.repository = repository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public JpaRepository<Order, Long> getRepository() {
        return repository;
    }

    @Override
    protected void preSave(Order entity) {
        applicationEventPublisher.publishEvent(new PreSaveOrderEvent(this, entity));
    }

    @Override
    protected void postSave(Order savedEntity) {
        setStatus(savedEntity);
    }

    @Override
    public Boolean approveOrder(Long orderId) {
        var toReturn = repository.approveOrder(orderId) == 1;
        this.applicationEventPublisher.publishEvent(new PostApproveOrderEvent(this, orderId));
        return toReturn;
    }

    @Override
    public Boolean cancelOrder(Long orderId) {
        return repository.cancelOrder(orderId) == 1;
    }

    @Override
    protected void postFindAll(Page<Order> all) {
        all.stream().forEach(this::setStatus);
    }

    /***
     * As I used dto for saving the object the return wasn't fecthing all fields
     */
    @Override
    protected void postFindOne(Order order) {
        this.setStatus(order);
    }

    private void setStatus(Order order) {
        order.setStatus(order.getOrderStatus().getStatusDescription());
    }
}
