package com.pragma.talen.pool.adapters.rest.client;

import com.pragma.talen.pool.domain.input_ports.client.ImagesPort;
import com.pragma.talen.pool.domain.models.client.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping(ImagesResources.IMAGES)
public class ImagesResources {
    static final String IMAGES = "/api/v1/images";

    private final ImagesPort imagesPort;

    @Autowired
    public ImagesResources(ImagesPort imagesPort) {
        this.imagesPort = imagesPort;
    }

    @GetMapping
    public Stream<Image> readAll() {
        return this.imagesPort.readAll();
    }

    @GetMapping("/{id}")
    public Optional<Image> read(@PathVariable long id) {
        return this.imagesPort.read(id);
    }

    @PostMapping
    public Image create(@RequestParam MultipartFile file, @RequestParam Long id) {
        return this.imagesPort.create(file, id);
    }

    @PutMapping("/{id}")
    public Image update(@PathVariable Long id, @RequestParam MultipartFile file) {
        return this.imagesPort.update(id, file);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return this.imagesPort.delete(id);
    }
}
