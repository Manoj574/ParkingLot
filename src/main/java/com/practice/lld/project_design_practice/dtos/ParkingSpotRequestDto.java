package com.practice.lld.project_design_practice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingSpotRequestDto {

    private String name;

    private String vehicleNumber;

    private ParkingSpotType parkingSpotType;
}
