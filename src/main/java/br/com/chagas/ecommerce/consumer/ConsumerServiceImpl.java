package br.com.chagas.ecommerce.consumer;

import br.com.chagas.ecommerce.framework.CrudServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl extends CrudServiceImpl<Consumer, Long> implements ConsumerService {

    private final ConsumerRepository repository;

    public ConsumerServiceImpl(ConsumerRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Consumer, Long> getRepository() {
        return repository;
    }
}
