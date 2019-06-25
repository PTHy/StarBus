package com.starbus.starbus.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Route {
    @Id
    @NotEmpty
    @Column(nullable = false)
    private String id;

    @OneToMany(targetEntity = Vehicle.class)
    private List<Vehicle> vehicles;

    @ManyToOne(targetEntity = RouteType.class)
    private RouteType routeType;

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
    private String startNodeId;

    @NotEmpty
    @Column(nullable = false)
    private String endNodeId;

    @NotEmpty
    @Column(nullable = false)
    private int startVehicleTime;

    @NotEmpty
    @Column(nullable = false)
    private LocalDateTime endVehicleTime;

    @NotEmpty
    @Column(nullable = false)
    private int intervalSatTime;

    @NotEmpty
    @Column(nullable = false)
    private int intervalSunTime;

    @NotEmpty
    @Column(nullable = false)
    private int intervalTime;
}
