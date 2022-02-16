package com.pragma.talen.pool.adapters.mongodb.daos.client;

import com.pragma.talen.pool.adapters.mongodb.entity.client.ImageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageMongoRepository extends MongoRepository<ImageEntity, Long> {
    void deleteAllByPersonEntity_Id(Long id);
}
