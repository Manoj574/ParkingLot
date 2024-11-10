package com.practice.lld.project_design_practice.repository;

import com.practice.lld.project_design_practice.dtos.ParkingSpotType;
import com.practice.lld.project_design_practice.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Integer> {

    List<ParkingSpot> findByType(final ParkingSpotType type);
}
