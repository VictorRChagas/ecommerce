package br.com.chagas.ecommerce.delivery;

import br.com.chagas.ecommerce.framework.CrudServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl extends CrudServiceImpl<Delivery, Long> implements DeliveryService {

    private DeliveryRepository repository;

    public DeliveryServiceImpl(DeliveryRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Delivery, Long> getRepository() {
        return repository;
    }
}
