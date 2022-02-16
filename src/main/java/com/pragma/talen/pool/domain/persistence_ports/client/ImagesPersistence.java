package com.pragma.talen.pool.domain.persistence_ports.client;

import com.pragma.talen.pool.domain.models.client.Image;
import com.pragma.talen.pool.domain.models.client.Person;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface ImagesPersistence {

    Stream<Image> readAll();

    boolean existImage(long id);

    Optional<Image> read(long id);

    Image create(MultipartFile file, Person person);

    Image update(Long id, MultipartFile file);

    String delete(Long id);

    void deleteAll(Long id);
}
