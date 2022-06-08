package com.parceldelivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    private Long id;

    private String name;

    private String email;

}
