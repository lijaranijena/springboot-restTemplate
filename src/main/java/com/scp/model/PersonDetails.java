package com.scp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonDetails {

    private String firstName;
    private String lastName;
    private String place;
    private int age;

}
