package com.pragma.talen.pool.adapters.mongodb.persistence.client;

import com.pragma.talen.pool.adapters.mongodb.daos.client.ImageMongoRepository;
import com.pragma.talen.pool.adapters.mongodb.entity.client.ImageEntity;
import com.pragma.talen.pool.adapters.mongodb.entity.client.PersonEntity;
import com.pragma.talen.pool.domain.exceptions.InternalExceptio;
import com.pragma.talen.pool.domain.models.client.Image;
import com.pragma.talen.pool.domain.models.client.Person;
import com.pragma.talen.pool.domain.persistence_ports.client.ImagesPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Repository("imagesPersistenceMongo")
public class ImagePersistenceMongo implements ImagesPersistence {
    private final ImageMongoRepository imageRepository;

    @Autowired
    public ImagePersistenceMongo(ImageMongoRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Stream<Image> readAll() {
        return this.imageRepository.findAll().stream().map(ImageEntity::toImage);
    }

    @Override
    public boolean existImage(long id) {
        return this.imageRepository.existsById(id);
    }

    @Override
    public Optional<Image> read(long id) {
        return Optional.of(this.imageRepository.findById(id).orElse(new ImageEntity()).toImage());
    }

    @Override
    public Image create(MultipartFile file, Person person) {
        PersonEntity personEntity = new PersonEntity(person);
        try {
            ImageEntity image = new ImageEntity(file.getBytes(), personEntity);
            return this.imageRepository.save(image).toImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new InternalExceptio("Error in the Server, MongoDb");
    }

    @Override
    public Image update(Long id, MultipartFile file) {
        ImageEntity image = this.imageRepository.findById(id).orElse(new ImageEntity());
        try {
            image.setImageData(file.getBytes());
            return this.imageRepository.save(image).toImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new InternalExceptio("Error in the Server, Mongo Update");
    }

    @Override
    public String delete(Long id) {
        this.imageRepository.deleteById(id);
        return "Image Delete";
    }

    @Override
    public void deleteAll(Long id) {
        this.imageRepository.deleteAllByPersonEntity_Id(id);
    }
}
