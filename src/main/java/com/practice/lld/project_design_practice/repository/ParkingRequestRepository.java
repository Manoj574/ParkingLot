package com.practice.lld.project_design_practice.repository;

import com.practice.lld.project_design_practice.model.ParkingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRequestRepository extends JpaRepository<ParkingRequest, Long> {
}
