package com.practice.lld.project_design_practice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "parking_request")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingRequest {

    @Id
    @Generated
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long requestId;

    @Basic
    private String name;

    @Basic
    @Column(name = "vehicle_number")
    private String vehicleNumber;

    @Basic
    @Column(name = "entry_date")
    private Date entryDate;

    @Basic
    @Column(name = "exit_date")
    private Date exitDate;

    @Basic
    private Long amount;

    @Basic
    @Enumerated(EnumType.STRING)
    private ParkingRequestStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_spot_id")
    private ParkingSpot parkingSpot;
}
