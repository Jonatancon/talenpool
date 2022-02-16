package com.pragma.talen.pool.domain.services.client;

import com.pragma.talen.pool.DataMock;
import com.pragma.talen.pool.domain.exceptions.ConflictException;
import com.pragma.talen.pool.domain.exceptions.NotFountException;
import com.pragma.talen.pool.domain.models.client.Person;
import com.pragma.talen.pool.domain.persistence_ports.client.ImagesPersistence;
import com.pragma.talen.pool.domain.persistence_ports.client.PersonPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ImagesServiceTest {
    @InjectMocks
    private ImagesService imagesService;
    @Mock
    private PersonPersistence personPersistence;
    @Mock
    private ImagesPersistence imagesPersistence;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void readAll() {
        when(imagesPersistence.readAll()).thenReturn(DataMock.readAllImages());
        assertNotNull(imagesService.readAll());
    }

    @Test
    void read() {
        when(imagesPersistence.existImage(anyLong())).thenReturn(true);
        when(imagesPersistence.read(anyLong())).thenReturn(Optional.of(DataMock.dataImage.get(1)));
        assertNotNull(imagesService.read(1L));
    }

    @Test
    void readFailNotExist() {
        when(imagesPersistence.existImage(anyLong())).thenReturn(false);
        assertThrowsExactly(NotFountException.class, ()-> imagesService.read(1L));
    }

    @Test
    void create() {
        when(personPersistence.existPersonById(anyLong())).thenReturn(true);
        when(personPersistence.read(anyLong())).thenReturn(Optional.of(DataMock.dataPerson.get(0)));
        when(imagesPersistence.create(any(MultipartFile.class), any(Person.class))).thenReturn(DataMock.dataImage.get(1));

        assertNotNull(imagesService.create(DataMock.fileMock, 1L));
    }

    @Test
    void createFailVerifyImage() {
        assertThrowsExactly(ConflictException.class, ()-> imagesService.create(DataMock.fileMockFail, 1L));
    }

    @Test
    void createFailExistPerson() {
        when(personPersistence.existPersonById(anyLong())).thenReturn(false);

        assertThrowsExactly(NotFountException.class, ()-> imagesService.create(DataMock.fileMock, 1L));
    }

    @Test
    void update() {
        when(imagesPersistence.existImage(anyLong())).thenReturn(true);
        when(imagesPersistence.update(anyLong(), any(MultipartFile.class))).thenReturn(DataMock.dataImage.get(1));

        assertNotNull(imagesService.update(1L, DataMock.fileMock));
    }

    @Test
    void updateFailExistImage() {
        when(imagesPersistence.existImage(anyLong())).thenReturn(false);
        assertThrowsExactly(NotFountException.class, ()-> imagesService.update(1L, DataMock.fileMock));
    }

    @Test
    void delete() {
        when(imagesPersistence.existImage(anyLong())).thenReturn(true);
        when(imagesPersistence.delete(anyLong())).thenReturn("Delete");

        assertNotNull(imagesService.delete(1L));
    }

    @Test
    void deleteFailExist() {
        when(imagesPersistence.existImage(anyLong())).thenReturn(false);
        assertThrowsExactly(NotFountException.class, ()-> imagesService.delete(1L));
    }

    @Test
    void deleteAllImagesFromPerson() {
        assertDoesNotThrow(()-> imagesService.deleteAllImagesFromPerson(1L));
    }
}