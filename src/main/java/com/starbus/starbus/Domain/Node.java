package com.starbus.starbus.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
public class Node {
    @Id
    @NotEmpty
    @Column(nullable = false)
    private String id;

    @NotEmpty
    @Column(nullable = false)
    private int cityId;

    @NotEmpty
    @Column(nullable = false)
    private int num;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Column(nullable = false)
    private double lat;

    @NotEmpty
    @Column(nullable = false)
    private double lon;

}
