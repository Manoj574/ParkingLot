package com.practice.lld.project_design_practice.model;

import com.practice.lld.project_design_practice.dtos.ParkingSpotType;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "ParkingSpot")
@Entity
@Setter
@Getter
public class ParkingSpot {

    @Id
    @Generated
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Basic
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private ParkingSpotType type;

    @Basic
    @Column(name = "occupied")
    private Boolean occupied;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ParkingRequest> parkingRequests;

    public Boolean isOccupied() {
        return occupied;
    }

    public boolean isEmpty() {
        return !occupied;
    }
}
