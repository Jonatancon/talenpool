package com.pragma.talen.pool;

import com.pragma.talen.pool.adapters.mysqldb.client.entities.client.ImageEntity;
import com.pragma.talen.pool.adapters.mysqldb.client.entities.client.PersonEntity;
import com.pragma.talen.pool.domain.models.client.Image;
import com.pragma.talen.pool.domain.models.client.Person;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class DataMock{

    public static MockMultipartFile fileMock = new MockMultipartFile("data", "filename.txt",
            "text/plain", "sdfsdsc".getBytes());

    public static MockMultipartFile fileMockFail = new MockMultipartFile("data", "filename.txt",
            "text/plain", "".getBytes());

    public static List<Person> dataPerson = List.of(
            new Person(1L, "Jonathan", "Restrepo", "email@pragma.com",
                    "112 321 1144", "CR 50", "Any City"),
            new Person(2L, "Jon", "Restrepo", "email@pragma.com",
                    "112 321 1144", "CR 50", "Any City"),
            new Person(3L, "Joana", "Restrepo", "email@pragma.com",
                    "112 321 1144", "CR 50", "Any City"),
            new Person(4L, "Juan", "Restrepo", "email@pragma.com",
                    "112 321 1144", "CR 50", "Any City"),
            new Person(5L, "    ", "Restrepo", "email@pragma.com",
                    "112 321 1144", "CR 50", "Any City"),
            new Person(6L, "name", "Restrepo", "email pragma.com",
                               "112 321 1144", "CR 50", "Any City")
    );

    public static List<Image> dataImage;

    static {
        try {
            dataImage = List.of(
                    new Image(1L, fileMock.getBytes(), dataPerson.get(1), fileMock.hashCode()),
                    new Image(2L, fileMock.getBytes(), dataPerson.get(1), fileMock.hashCode()),
                    new Image(3L, fileMock.getBytes(), dataPerson.get(1), fileMock.hashCode()),
                    new Image(4L, fileMock.getBytes(), dataPerson.get(1), fileMock.hashCode()),
                    new Image(5L, fileMock.getBytes(), dataPerson.get(1), fileMock.hashCode()),
                    new Image(6L, fileMock.getBytes(), dataPerson.get(1), fileMock.hashCode())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<PersonEntity> dataPersonMysql = List.of(
            new PersonEntity(dataPerson.get(1)),
            new PersonEntity(dataPerson.get(1)),
            new PersonEntity(dataPerson.get(1)),
            new PersonEntity(dataPerson.get(1)),
            new PersonEntity(dataPerson.get(1))
    );

    public static List<ImageEntity> dataImageMysql;

    static {
        try {
            dataImageMysql = List.of(
                    new ImageEntity(fileMock.getBytes(), dataPersonMysql.get(1)),
                    new ImageEntity(fileMock.getBytes(), dataPersonMysql.get(1)),
                    new ImageEntity(fileMock.getBytes(), dataPersonMysql.get(1)),
                    new ImageEntity(fileMock.getBytes(), dataPersonMysql.get(1)),
                    new ImageEntity(fileMock.getBytes(), dataPersonMysql.get(1)),
                    new ImageEntity(fileMock.getBytes(), dataPersonMysql.get(1))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Stream<Person> readAll() {
        return dataPerson.stream();
    }

    public static Stream<Image> readAllImages() {
        return dataImage.stream();
    }


}
