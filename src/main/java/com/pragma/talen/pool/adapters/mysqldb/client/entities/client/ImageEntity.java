package com.pragma.talen.pool.adapters.mysqldb.client.entities.client;

import com.pragma.talen.pool.domain.models.client.Image;
import com.pragma.talen.pool.domain.models.client.Person;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Arrays;

@Getter
@Setter
@Entity
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] imageData;
    @ManyToOne
    private PersonEntity personEntity;

    public ImageEntity() {
        // For Frame ware
    }

    public ImageEntity(byte[] imageData, PersonEntity personEntity) {
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
