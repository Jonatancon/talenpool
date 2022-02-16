package com.pragma.talen.pool.adapters.mongodb.persistence.client;

import com.pragma.talen.pool.DataMock;
import com.pragma.talen.pool.adapters.mongodb.daos.client.ImageMongoRepository;
import com.pragma.talen.pool.adapters.mongodb.entity.client.ImageEntity;
import com.pragma.talen.pool.adapters.mongodb.entity.client.PersonEntity;
import com.pragma.talen.pool.domain.exceptions.InternalExceptio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ImagePersistenceMongoTest {
    @InjectMocks
    private ImagePersistenceMongo imagePersistenceMongo;
    @Mock
    private ImageMongoRepository imageRepository;

    public static List<ImageEntity> dataImageMongo;

    static {
        try {
            dataImageMongo = List.of(
                    new ImageEntity(DataMock.fileMock.getBytes(), new PersonEntity(DataMock.dataPerson.get(0))),
                    new ImageEntity(DataMock.fileMock.getBytes(), new PersonEntity(DataMock.dataPerson.get(0))),
                    new ImageEntity(DataMock.fileMock.getBytes(), new PersonEntity(DataMock.dataPerson.get(0))),
                    new ImageEntity(DataMock.fileMock.getBytes(), new PersonEntity(DataMock.dataPerson.get(0))),
                    new ImageEntity(DataMock.fileMock.getBytes(), new PersonEntity(DataMock.dataPerson.get(0)))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Mock
    MockMultipartFile fileMock = new MockMultipartFile("data", "filename.txt",
            "text/plain", "".getBytes());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void readAll() {
        when(imageRepository.findAll()).thenReturn(dataImageMongo);
        assertNotNull(imagePersistenceMongo.readAll());
    }

    @Test
    void existImageTrue() {
        when(imageRepository.existsById(anyLong())).thenReturn(true);
        assertTrue(imagePersistenceMongo.existImage(1L));
    }

    @Test
    void existImageFalse() {
        when(imageRepository.existsById(anyLong())).thenReturn(false);
        assertFalse(imagePersistenceMongo.existImage(1L));
    }

    @Test
    void read() {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(dataImageMongo.get(0)));
        assertNotNull(imagePersistenceMongo.read(1L));
    }

    @Test
    void create() {
        when(imageRepository.save(any(ImageEntity.class))).thenReturn(dataImageMongo.get(0));
        assertNotNull(imagePersistenceMongo.create(DataMock.fileMock, DataMock.dataPerson.get(0)));
    }

    @Test
    void createFailCreate() throws IOException {
        when(imageRepository.save(any(ImageEntity.class))).thenReturn(dataImageMongo.get(0));
        when(fileMock.getBytes()).thenThrow(IOException.class);
        assertThrowsExactly(InternalExceptio.class, () -> imagePersistenceMongo.create(fileMock, DataMock.dataPerson.get(0)));
    }

    @Test
    void update() {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(dataImageMongo.get(0)));
        when(imageRepository.save(any(ImageEntity.class))).thenReturn(dataImageMongo.get(0));
        assertNotNull(imagePersistenceMongo.update(1L, DataMock.fileMock));
    }

    @Test
    void updateFail() throws IOException {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(dataImageMongo.get(0)));
        when(imageRepository.save(any(ImageEntity.class))).thenReturn(dataImageMongo.get(0));
        when(fileMock.getBytes()).thenThrow(IOException.class);
        assertThrowsExactly(InternalExceptio.class, () -> imagePersistenceMongo.update(1L, fileMock));
    }

    @Test
    void delete() {
        assertEquals("Image Delete", imagePersistenceMongo.delete(1L));
    }

    @Test
    void deleteAll() {
        assertDoesNotThrow(() -> imagePersistenceMongo.deleteAll(1L));
    }
}