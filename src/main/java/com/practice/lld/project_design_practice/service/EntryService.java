package com.practice.lld.project_design_practice.service;

import com.practice.lld.project_design_practice.dtos.ParkingSpotRequestDto;
import com.practice.lld.project_design_practice.dtos.ParkingSpotResponseDto;
import com.practice.lld.project_design_practice.model.ParkingRequest;
import com.practice.lld.project_design_practice.model.ParkingRequestStatus;
import com.practice.lld.project_design_practice.model.ParkingSpot;
import com.practice.lld.project_design_practice.repository.ParkingRequestRepository;
import com.practice.lld.project_design_practice.repository.ParkingSpotRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class EntryService {

    private final ParkingSpotRepository parkingSpotRepository;

    private final ParkingRequestRepository parkingRequestRepository;

    public EntryService(final ParkingSpotRepository parkingSpotRepository, ParkingRequestRepository parkingRequestRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingRequestRepository = parkingRequestRepository;
    }

    public ParkingSpotResponseDto reserveParkingSpot(final ParkingSpotRequestDto requestDto) {

        final Optional<ParkingSpot> parkingSpot = getParkingSpot(requestDto);
        if (parkingSpot.isPresent()) {
            ParkingRequest parkingRequest= ParkingRequest.builder().parkingSpot(parkingSpot.get())
                    .name(requestDto.getName())
                    .vehicleNumber(requestDto.getVehicleNumber())
                    .entryDate(new Date())
                    .status(ParkingRequestStatus.ENTERED)
                    .build();
            parkingSpot.get().setOccupied(Boolean.TRUE);
            parkingRequest.setParkingSpot(parkingSpot.get());
            parkingSpot.get().getParkingRequests().add(parkingRequest);
            ParkingRequest savedRequest = parkingRequestRepository.save(parkingRequest);
            return ParkingSpotResponseDto.getSuccessResponse(parkingRequest);
            //save and return positve
        } else {
            //negative
            return ParkingSpotResponseDto.getFailureResponse("Unable to find Parking spot");
        }
    }

    private Optional<ParkingSpot> getParkingSpot(ParkingSpotRequestDto requestDto) {
        return parkingSpotRepository.findByType(requestDto.getParkingSpotType())
                .stream()
                .filter(ParkingSpot::isEmpty)
                .findAny();
    }
}
