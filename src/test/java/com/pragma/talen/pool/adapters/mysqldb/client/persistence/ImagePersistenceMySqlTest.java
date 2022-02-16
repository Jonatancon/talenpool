package com.pragma.talen.pool.adapters.mysqldb.client.persistence;

import com.pragma.talen.pool.DataMock;
import com.pragma.talen.pool.adapters.mysqldb.client.daos.ImageRepository;
import com.pragma.talen.pool.adapters.mysqldb.client.entities.client.ImageEntity;
import com.pragma.talen.pool.domain.exceptions.InternalExceptio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ImagePersistenceMySqlTest {
    @InjectMocks
    private ImagePersistenceMySql imagePersistenceMySql;
    @Mock
    private ImageRepository imageRepository;
    @Mock
    MockMultipartFile fileMock = new MockMultipartFile("data", "filename.txt",
            "text/plain", "".getBytes());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void readAll() {
        when(imageRepository.findAll()).thenReturn(DataMock.dataImageMysql);
        assertNotNull(imagePersistenceMySql.readAll());
    }

    @Test
    void existImageTrue() {
        when(imageRepository.existsById(anyLong())).thenReturn(true);
        assertTrue(imagePersistenceMySql.existImage(1L));
    }

    @Test
    void existImageFalse() {
        when(imageRepository.existsById(anyLong())).thenReturn(false);
        assertFalse(imagePersistenceMySql.existImage(1L));
    }

    @Test
    void read() {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(DataMock.dataImageMysql.get(0)));
        assertNotNull(imagePersistenceMySql.read(1L));
    }

    @Test
    void create() {
        when(imageRepository.save(any(ImageEntity.class))).thenReturn(DataMock.dataImageMysql.get(0));
        assertNotNull(imagePersistenceMySql.create(DataMock.fileMock, DataMock.dataPerson.get(0)));
    }

    @Test
    void createFailCreate() throws IOException {
        when(imageRepository.save(any(ImageEntity.class))).thenReturn(DataMock.dataImageMysql.get(0));
        when(fileMock.getBytes()).thenThrow(IOException.class);
        assertThrowsExactly(InternalExceptio.class, () -> imagePersistenceMySql.create(fileMock, DataMock.dataPerson.get(0)));
    }

    @Test
    void update() {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(DataMock.dataImageMysql.get(0)));
        when(imageRepository.save(any(ImageEntity.class))).thenReturn(DataMock.dataImageMysql.get(0));
        assertNotNull(imagePersistenceMySql.update(1L, DataMock.fileMock));
    }

    @Test
    void updateFail() throws IOException {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(DataMock.dataImageMysql.get(0)));
        when(imageRepository.save(any(ImageEntity.class))).thenReturn(DataMock.dataImageMysql.get(0));
        when(fileMock.getBytes()).thenThrow(IOException.class);
        assertThrowsExactly(InternalExceptio.class, () -> imagePersistenceMySql.update(1L, fileMock));
    }

    @Test
    void delete() {
        assertEquals("Image Delete", imagePersistenceMySql.delete(1L));
    }

    @Test
    void deleteAll() {
        assertDoesNotThrow(() -> imagePersistenceMySql.deleteAll(1L));
    }
}