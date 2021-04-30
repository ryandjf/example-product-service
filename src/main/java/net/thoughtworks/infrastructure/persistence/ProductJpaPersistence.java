package net.thoughtworks.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.thoughtworks.infrastructure.dataentity.ProductDataEntity;

@Repository
public interface ProductJpaPersistence extends CrudRepository<ProductDataEntity, Long> {

}
