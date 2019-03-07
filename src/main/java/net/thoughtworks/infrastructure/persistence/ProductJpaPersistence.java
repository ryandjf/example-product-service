package net.thoughtworks.infrastructure.persistence;

import net.thoughtworks.infrastructure.dataentity.ProductDataEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaPersistence extends CrudRepository<ProductDataEntity, Long> {

}
