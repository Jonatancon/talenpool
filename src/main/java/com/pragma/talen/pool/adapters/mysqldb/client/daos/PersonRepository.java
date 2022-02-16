package com.pragma.talen.pool.adapters.mysqldb.client.daos;

import com.pragma.talen.pool.adapters.mysqldb.client.entities.client.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    boolean existsPersonEntityById(Long id);
    boolean existsPersonEntityByEmail(String email);
}
