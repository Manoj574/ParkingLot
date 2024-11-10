package com.practice.lld.project_design_practice.dtos;

import com.practice.lld.project_design_practice.model.ParkingRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpotResponseDto {

    private Long invoiceId;

    private Integer spotId;

    private ParkingSpotType parkingSpotType;

    private Date entryDate;

    private String status;

    private String errorMsg;

    public ParkingSpotResponseDto(Long invoiceId, Integer spotId, ParkingSpotType parkingSpotType, Date entryDate, String status) {
        this.invoiceId = invoiceId;
        this.spotId = spotId;
        this.parkingSpotType = parkingSpotType;
        this.entryDate = entryDate;
        this.status = status;
    }

    public ParkingSpotResponseDto(String status, String errorMsg) {
        this.status = status;
        this.errorMsg = errorMsg;
    }

    public static ParkingSpotResponseDto getFailureResponse(String msg) {

        return new ParkingSpotResponseDto("FAILED", msg);
    }

    public static ParkingSpotResponseDto getSuccessResponse(ParkingRequest parkingRequest) {

        return new ParkingSpotResponseDto(parkingRequest.getRequestId(), parkingRequest.getParkingSpot().getId(),
                parkingRequest.getParkingSpot().getType(), parkingRequest.getEntryDate(), "SUCCESSFUL");

    }


}
