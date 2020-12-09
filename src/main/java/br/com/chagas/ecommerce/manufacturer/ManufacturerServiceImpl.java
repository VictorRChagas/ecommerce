package br.com.chagas.ecommerce.manufacturer;

import br.com.chagas.ecommerce.framework.CrudServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerServiceImpl extends CrudServiceImpl<Manufacturer, Long> implements ManufacturerService {

    private final ManufacturerRepository repository;

    public ManufacturerServiceImpl(ManufacturerRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Manufacturer, Long> getRepository() {
        return repository;
    }
}
