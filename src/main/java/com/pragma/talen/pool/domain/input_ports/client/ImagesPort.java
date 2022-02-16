package com.pragma.talen.pool.domain.input_ports.client;

import com.pragma.talen.pool.domain.models.client.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.stream.Stream;

public interface ImagesPort {
    Stream<Image> readAll();

    Optional<Image> read(long id);

    Image create(MultipartFile file, Long id);

    Image update(Long id, MultipartFile file);

    String delete(Long id);

    void deleteAllImagesFromPerson(Long id);
}
