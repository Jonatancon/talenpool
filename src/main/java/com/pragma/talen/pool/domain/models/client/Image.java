package com.pragma.talen.pool.domain.models.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private Long id;
    @JsonIgnore
    private byte[] data;
    private Person person;
    private Integer hasCode;

}
