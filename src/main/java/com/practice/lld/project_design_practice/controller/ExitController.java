package com.practice.lld.project_design_practice.controller;

import com.practice.lld.project_design_practice.dtos.BillPaymentResponseDto;
import com.practice.lld.project_design_practice.model.ParkingRequest;
import com.practice.lld.project_design_practice.model.ParkingRequestStatus;
import com.practice.lld.project_design_practice.repository.ParkingRequestRepository;
import com.practice.lld.project_design_practice.strategy.PaymentCalculationStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/parkingSpot/exit")
public class ExitController {

    private final ParkingRequestRepository parkingRequestRepository;

    private final PaymentCalculationStrategy paymentCalculationStrategy;

    public ExitController(ParkingRequestRepository parkingRequestRepository, PaymentCalculationStrategy paymentCalculationStrategy) {

        this.parkingRequestRepository = parkingRequestRepository;
        this.paymentCalculationStrategy = paymentCalculationStrategy;
    }

    @GetMapping
    public ResponseEntity<BillPaymentResponseDto> generateBill(@RequestParam(name = "requestId", required = true) Long requestId) {

        Optional<ParkingRequest> parkingRequestOptional = parkingRequestRepository.findById(requestId);
        if (parkingRequestOptional.isPresent()) {
            ParkingRequest parkingRequest = parkingRequestOptional.get();
            if (parkingRequest.getStatus() == ParkingRequestStatus.ENTERED) {
                parkingRequest.setExitDate(new Date());
                long billAmount = paymentCalculationStrategy.calculatePayment(parkingRequest);
                parkingRequest.setAmount(billAmount);

                parkingRequestRepository.save(parkingRequest);

                return ResponseEntity.ok(BillPaymentResponseDto.getSuccessfulReponse(parkingRequest, billAmount));
            }
            return ResponseEntity.ok(BillPaymentResponseDto.getFailureReponse("Request already processed"));
        }

        return new ResponseEntity<>(BillPaymentResponseDto.getFailureReponse("Request Not found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/savePayment")
    public ResponseEntity<Void> savePayment(@RequestParam(name = "requestId", required = true) Long requestId) {

        Optional<ParkingRequest> parkingRequestOptional = parkingRequestRepository.findById(requestId);

        if (parkingRequestOptional.isPresent()) {
            ParkingRequest parkingRequest = parkingRequestOptional.get();
            parkingRequest.getParkingSpot().setOccupied(false);
            parkingRequest.setStatus(ParkingRequestStatus.EXITED);
            parkingRequestRepository.save(parkingRequest);
            return ResponseEntity.ok().build();
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    }
