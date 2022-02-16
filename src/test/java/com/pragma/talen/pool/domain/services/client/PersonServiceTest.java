package com.pragma.talen.pool.domain.services.client;

import com.pragma.talen.pool.DataMock;
import com.pragma.talen.pool.domain.exceptions.ConflictException;
import com.pragma.talen.pool.domain.exceptions.NotFountException;
import com.pragma.talen.pool.domain.models.client.Person;
import com.pragma.talen.pool.domain.persistence_ports.client.PersonPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class PersonServiceTest {

    @InjectMocks
    private PersonService personService;
    @Mock
    private PersonPersistence personPersistence;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void readAll() {
        when(personPersistence.readAll()).thenReturn(DataMock.readAll());
        assertNotNull(personService.readAll());
    }

    @Test
    void create() {
        when(personPersistence.create(any(Person.class))).thenReturn(DataMock.dataPerson.get(1));
        when(personPersistence.existPersonByEmail(anyString())).thenReturn(false);
        assertNotNull(personService.create(DataMock.dataPerson.get(1)));
    }

    @Test
    void createFailExistPerson() {
        when(personPersistence.create(any(Person.class))).thenReturn(DataMock.dataPerson.get(1));
        when(personPersistence.existPersonByEmail(anyString())).thenReturn(false);
        when(personPersistence.existPersonById(anyLong())).thenReturn(true);

        assertThrowsExactly(ConflictException.class, () -> personService.create(DataMock.dataPerson.get(1)));
    }

    @Test
    void createFailPersonInfo() {
        assertThrowsExactly(ConflictException.class, () -> personService.create(DataMock.dataPerson.get(4)));
    }

    @Test
    void createFailEmail() {
        assertThrowsExactly(ConflictException.class, () -> personService.create(DataMock.dataPerson.get(5)));
    }

    @Test
    void createFailEmailExist() {
        when(personPersistence.existPersonByEmail(anyString())).thenReturn(true);
        assertThrowsExactly(ConflictException.class, () -> personService.create(DataMock.dataPerson.get(1)));
    }

    @Test
    void update() {
        when(personPersistence.existPersonById(anyLong())).thenReturn(true);
        when(personPersistence.update(any(Person.class))).thenReturn(DataMock.dataPerson.get(1));

        assertNotNull(personService.update(DataMock.dataPerson.get(2)));
    }

    @Test
    void updateFailNotExistPerson() {
        when(personPersistence.existPersonById(anyLong())).thenReturn(false);

        assertThrowsExactly(NotFountException.class, ()-> personService.update(DataMock.dataPerson.get(2)));
    }

    @Test
    void read() {
        when(personPersistence.existPersonById(anyLong())).thenReturn(true);
        when(personPersistence.read(anyLong())).thenReturn(Optional.of(DataMock.dataPerson.get(1)));

        assertNotNull(personService.read(1L));
    }

    @Test
    void readFailNotFount() {
        when(personPersistence.existPersonById(anyLong())).thenReturn(false);

        assertThrowsExactly(NotFountException.class, ()-> personService.read(1L));
    }

    @Test
    void deleteFailNotFountPersonForDelete() {
        when(personPersistence.existPersonById(anyLong())).thenReturn(false);

        assertThrowsExactly(NotFountException.class, ()-> personService.delete(1L));
    }

    @Test
    void delete() {
        when(personPersistence.existPersonById(anyLong())).thenReturn(true);
        when(personPersistence.delete(anyLong())).thenReturn("Person Delete");

        assertNotNull(personService.delete(1L));
    }
}