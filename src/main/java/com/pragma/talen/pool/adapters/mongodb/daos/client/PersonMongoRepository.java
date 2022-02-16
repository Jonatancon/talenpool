package com.pragma.talen.pool.adapters.mongodb.daos.client;

import com.pragma.talen.pool.adapters.mongodb.entity.client.PersonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonMongoRepository extends MongoRepository<PersonEntity, Long> {
}
