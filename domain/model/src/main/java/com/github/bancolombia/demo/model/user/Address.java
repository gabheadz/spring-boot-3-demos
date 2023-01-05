package com.github.bancolombia.demo.model.user;

import lombok.Builder;
import lombok.Data;

@Data
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
}
