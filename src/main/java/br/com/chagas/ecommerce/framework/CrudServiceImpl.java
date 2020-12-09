package br.com.chagas.ecommerce.framework;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import javax.persistence.NoResultException;

public abstract class CrudServiceImpl<T, ID> implements CrudService<T, ID> {

    public abstract JpaRepository<T, ID> getRepository();

    @Override
    public Page<T> findAll(PageRequest pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public T findById(ID id) {
        return getRepository().findById((ID) id)
                .orElseThrow(NoResultException::new);
    }

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public ResponseEntity<?> deleteById(ID id) {
        if (getRepository().existsById(id)) {
            getRepository().deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new NoResultException();
    }
}
