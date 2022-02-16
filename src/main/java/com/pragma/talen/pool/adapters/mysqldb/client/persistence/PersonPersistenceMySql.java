package com.pragma.talen.pool.adapters.mysqldb.client.persistence;


import com.pragma.talen.pool.adapters.mysqldb.client.daos.PersonRepository;
import com.pragma.talen.pool.adapters.mysqldb.client.entities.client.PersonEntity;
import com.pragma.talen.pool.domain.models.client.Person;
import com.pragma.talen.pool.domain.persistence_ports.client.PersonPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository("personPersistence")
public class PersonPersistenceMySql implements PersonPersistence {

    private final PersonRepository personRepository;

    @Autowired
    public PersonPersistenceMySql(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Stream<Person> readAll() {
        return personRepository.findAll().stream().map(PersonEntity::toPerson);
    }

    @Override
    public boolean existPersonById(Long id) {
        return this.personRepository.existsPersonEntityById(id);
    }

    @Override
    public boolean existPersonByEmail(String email) {
        return this.personRepository.existsPersonEntityByEmail(email);
    }

    @Override
    public Person create(Person person) {
        return personRepository.save(new PersonEntity(person)).toPerson();
    }

    @Override
    public Person update(Person person) {
        return personRepository.save(new PersonEntity(person)).toPerson();
    }

    @Override
    public Optional<Person> read(Long id) {
        return Optional.of(personRepository.findById(id).orElse(new PersonEntity()).toPerson());
    }

    @Override
    public String delete(Long id) {
        this.personRepository.deleteById(id);
        return "Person is Delete with all your Images";
    }
}
