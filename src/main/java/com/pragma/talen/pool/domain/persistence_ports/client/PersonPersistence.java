package com.pragma.talen.pool.domain.persistence_ports.client;

import com.pragma.talen.pool.domain.models.client.Person;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface PersonPersistence {
    Stream<Person> readAll();

    boolean existPersonById(Long id);

    boolean existPersonByEmail(String email);

    Person create(Person person);

    Person update(Person person);

    Optional<Person> read(Long id);

    String delete(Long id);
}
