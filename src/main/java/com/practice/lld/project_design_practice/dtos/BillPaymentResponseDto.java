package com.practice.lld.project_design_practice.dtos;

import com.practice.lld.project_design_practice.model.ParkingRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillPaymentResponseDto {

    private Long amount;

    private Long parkingRequestId;

    private String errorMessage;

    public BillPaymentResponseDto(Long amount, Long requestId) {
        this.amount = amount;
        this.parkingRequestId = requestId;
    }

    public BillPaymentResponseDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static BillPaymentResponseDto getSuccessfulReponse(ParkingRequest request, Long amount) {

        return new BillPaymentResponseDto(amount, request.getRequestId());
    }

    public static BillPaymentResponseDto getFailureReponse(String errorMessage) {

        return new BillPaymentResponseDto(errorMessage);
    }
}
