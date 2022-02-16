package com.pragma.talen.pool.adapters.mysqldb.client.daos;

import com.pragma.talen.pool.adapters.mysqldb.client.entities.client.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    void deleteAllByPersonEntity_Id(Long id);
}
