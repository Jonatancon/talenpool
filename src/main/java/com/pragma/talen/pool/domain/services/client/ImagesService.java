package com.pragma.talen.pool.domain.services.client;

import com.pragma.talen.pool.domain.exceptions.ConflictException;
import com.pragma.talen.pool.domain.exceptions.NotFountException;
import com.pragma.talen.pool.domain.input_ports.client.ImagesPort;
import com.pragma.talen.pool.domain.models.client.Image;
import com.pragma.talen.pool.domain.models.client.Person;
import com.pragma.talen.pool.domain.persistence_ports.client.ImagesPersistence;
import com.pragma.talen.pool.domain.persistence_ports.client.PersonPersistence;
import com.pragma.talen.pool.domain.util.ValidatorsUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ImagesService implements ImagesPort {

    private final ImagesPersistence imagesPersistence;
    private final PersonPersistence personPersistence;

    public ImagesService(@Qualifier("imagesPersistenceMongo") ImagesPersistence imagesPersistence,
                         @Qualifier("personPersistence")PersonPersistence personPersistence) {
        this.imagesPersistence = imagesPersistence;
        this.personPersistence = personPersistence;
    }

    @Override
    @Transactional(readOnly = true)
    public Stream<Image> readAll() {
        return this.imagesPersistence.readAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Image> read(long id) {
        if (this.notExistImage(id)) {
            throw new NotFountException("Not Fount Image In The Database");
        }

        return this.imagesPersistence.read(id);
    }

    @Override
    @Transactional
    public Image create(MultipartFile file, Long id) {
        this.verifyImage(file);
        this.existPerson(id);
        Person person = this.personPersistence.read(id).orElse(new Person());
        return this.imagesPersistence.create(file, person);
    }

    @Override
    @Transactional
    public Image update(Long id, MultipartFile file) {
        if (this.notExistImage(id)){
            throw new NotFountException("Not Fount Image in the database for update");
        }
        this.verifyImage(file);

        return this.imagesPersistence.update(id, file);
    }

    @Override
    @Transactional
    public String delete(Long id) {
        if (this.notExistImage(id)){
            throw new NotFountException("Impossible delete image, Not found");
        }
        return this.imagesPersistence.delete(id);
    }

    @Override
    @Transactional
    public void deleteAllImagesFromPerson(Long id) {
        this.imagesPersistence.deleteAll(id);
    }

    public boolean notExistImage(long id) {
        return !this.imagesPersistence.existImage(id);
    }

    public void verifyImage(MultipartFile file){
        if (ValidatorsUtil.isEmptyFile(file)) {
            throw new ConflictException("No Image upload, plase select a image");
        }
    }

    public void existPerson(Long id) {
        if (!this.personPersistence.existPersonById(id)) {
            throw new NotFountException("Not Found Person with id: " + id + " in the database");
        }
    }
}
