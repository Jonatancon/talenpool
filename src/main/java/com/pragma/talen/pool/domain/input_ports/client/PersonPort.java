package com.pragma.talen.pool.domain.input_ports.client;

import com.pragma.talen.pool.domain.models.client.Person;

import java.util.Optional;
import java.util.stream.Stream;

public interface PersonPort {
    Stream<Person> readAll();

    Person create(Person person);

    Person update(Person person);

    Optional<Person> read(Long id);

    String delete(Long id);
}
