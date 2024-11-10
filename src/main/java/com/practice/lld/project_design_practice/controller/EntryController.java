package com.practice.lld.project_design_practice.controller;

import com.practice.lld.project_design_practice.dtos.ParkingSpotRequestDto;
import com.practice.lld.project_design_practice.dtos.ParkingSpotResponseDto;
import com.practice.lld.project_design_practice.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkingSpot/entry")
public class EntryController {

    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping
    public ResponseEntity<ParkingSpotResponseDto> getInvoice(@RequestBody ParkingSpotRequestDto requestDto) {
        ParkingSpotResponseDto responseDto;
        try {
            responseDto = entryService.reserveParkingSpot(requestDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
        return responseDto.getStatus().equals("SUCCESSFUL") ? new ResponseEntity<>(responseDto, HttpStatus.CREATED)
                : new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
