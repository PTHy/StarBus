package com.starbus.starbus.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Vehicle {
    @Id
    private String id;

    @ManyToOne(targetEntity = Route.class)
    private Route route;
}
