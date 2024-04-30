package com.ssafy.sebakwi.product.domain;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WheelRepository extends JpaRepository<Wheel, Integer> {

    @Query("SELECT w FROM Wheel w WHERE w.serialNumber = :wheelSerialNumber")
    Wheel findByWheelSerialNumber(String wheelSerialNumber);

//    default boolean existByWheelSerialNumber(String wheelSerialNumber) {
//        return countByWheelSerialNumber(wheelSerialNumber) > 0;
//    }

//    default boolean existByWheelSerialNumber(String wheelSerialNumber) {
//        return countByWheelSerialNumber(wheelSerialNumber) > 0;
//    }
}
