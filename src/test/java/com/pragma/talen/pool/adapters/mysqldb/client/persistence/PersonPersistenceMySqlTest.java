package com.pragma.talen.pool.adapters.mysqldb.client.persistence;

import com.pragma.talen.pool.DataMock;
import com.pragma.talen.pool.adapters.mysqldb.client.daos.PersonRepository;
import com.pragma.talen.pool.adapters.mysqldb.client.entities.client.PersonEntity;
import com.pragma.talen.pool.domain.models.client.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class PersonPersistenceMySqlTest {

    @InjectMocks
    private PersonPersistenceMySql personPersistenceMySql;
    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void readAll() {
        when(personRepository.findAll()).thenReturn(DataMock.dataPersonMysql);
        assertNotNull(personPersistenceMySql.readAll());
    }

    @Test
    void existPersonByIdTrue() {
        when(personRepository.existsPersonEntityById(anyLong())).thenReturn(true);
        assertTrue(personPersistenceMySql.existPersonById(1L));
    }

    @Test
    void existPersonByIdFalse() {
        when(personRepository.existsPersonEntityById(anyLong())).thenReturn(false);
        assertFalse(personPersistenceMySql.existPersonById(1L));
    }

    @Test
    void existPersonByEmailTrue() {
        when(personRepository.existsPersonEntityByEmail(anyString())).thenReturn(true);
        assertTrue(personPersistenceMySql.existPersonByEmail("12134"));
    }

    @Test
    void existPersonByEmailFalse() {
        when(personRepository.existsPersonEntityByEmail(anyString())).thenReturn(false);
        assertFalse(personPersistenceMySql.existPersonByEmail("12134"));
    }

    @Test
    void create() {
        when(personRepository.save(any(PersonEntity.class))).thenReturn(DataMock.dataPersonMysql.get(0));
        assertNotNull(personPersistenceMySql.create(DataMock.dataPerson.get(0)));
    }

    @Test
    void update() {
        when(personRepository.save(any(PersonEntity.class))).thenReturn(DataMock.dataPersonMysql.get(0));
        assertNotNull(personPersistenceMySql.update(DataMock.dataPerson.get(0)));
    }

    @Test
    void read() {
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(DataMock.dataPersonMysql.get(1)));
        assertNotNull(personPersistenceMySql.read(1L));
    }

    @Test
    void delete() {
        assertEquals("Person is Delete with all your Images", personPersistenceMySql.delete(1L));
    }
}