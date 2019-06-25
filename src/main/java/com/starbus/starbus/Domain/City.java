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
public class City {
    @Id
    @NotEmpty
    @Column(nullable = false)
    private int code;

    @NotEmpty
    @Column(nullable = false)
    private String name;
}
