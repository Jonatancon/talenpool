package com.pragma.talen.pool.adapters.mongodb.entity.client;

import com.pragma.talen.pool.domain.models.client.Image;
import com.pragma.talen.pool.domain.models.client.Person;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

@Getter
@Setter
@Document
public class ImageEntity {

    @Id
    private Long id;
    @Lob
    private byte[] imageData;
    private PersonEntity personEntity;

    public ImageEntity() {
        this.id = UUID.randomUUID().getLeastSignificantBits();
    }

    public ImageEntity(byte[] imageData, PersonEntity personEntity) {
        this.id = UUID.randomUUID().getLeastSignificantBits();
        this.imageData = imageData;
        this.personEntity = personEntity;
    }

    public Image toImage() {
        Image image = new Image();
        Person person = this.getPersonEntity().toPerson();
        BeanUtils.copyProperties(this, image);
        image.setPerson(person);
        image.setHasCode(Arrays.hashCode(this.imageData));
        return image;
    }
}
