package com.practice.lld.project_design_practice.strategy;

import com.practice.lld.project_design_practice.model.ParkingRequest;

public interface PaymentCalculationStrategy {

    long calculatePayment(ParkingRequest parkingRequest);
}
