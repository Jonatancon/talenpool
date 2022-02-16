package com.pragma.talen.pool.adapters.mysqldb.client.entities.client;

import com.pragma.talen.pool.domain.models.client.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String address;
    private String location;


    public PersonEntity(Person person) {
        BeanUtils.copyProperties(person, this);
    }

    public Person toPerson() {
        Person person = new Person();
        BeanUtils.copyProperties(this, person);
        return person;
    }
}
