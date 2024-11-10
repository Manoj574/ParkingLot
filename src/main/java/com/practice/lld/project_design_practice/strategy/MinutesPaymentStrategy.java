package com.practice.lld.project_design_practice.strategy;

import com.practice.lld.project_design_practice.model.ParkingRequest;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class MinutesPaymentStrategy implements PaymentCalculationStrategy {

    private static final int CHARGE_PER_MINUTE = 1;

    @Override
    public long calculatePayment(ParkingRequest parkingRequest) {

        Instant exitDateInstant = parkingRequest.getExitDate().toInstant();
        Instant entryDateInstant = parkingRequest.getEntryDate().toInstant();
        long differenceInMinutes = Duration.between(entryDateInstant, exitDateInstant).toMinutes();

        return differenceInMinutes * CHARGE_PER_MINUTE;


    }
}
