package br.com.chagas.ecommerce.framework;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

public interface CrudService<T, ID> {
    Page<T> findAll(PageRequest pageable);;

    T findById(ID id);

    T save(T entity);

    ResponseEntity<?> deleteById(ID id);

}
