package com.pragma.talen.pool.domain.models.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String location;

}
