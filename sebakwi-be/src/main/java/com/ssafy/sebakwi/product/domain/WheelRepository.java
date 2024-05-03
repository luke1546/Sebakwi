package com.ssafy.sebakwi.product.domain;

import com.ssafy.sebakwi.product.dto.WheelMonthlyStatus;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WheelRepository extends JpaRepository<Wheel, Integer> {

    @Query("SELECT w FROM Wheel w WHERE w.serialNumber = :wheelSerialNumber")
    Wheel findByWheelSerialNumber(String wheelSerialNumber);

    /**
     * 메인페이지
     */

//    @Query("SELECT w FROM Wheel w" +
//            "LEFT JOIN FETCH w.")
//    List<WheelMonthlyStatus> findWheelMonthlyStatus();

//    default boolean existByWheelSerialNumber(String wheelSerialNumber) {
//        return countByWheelSerialNumber(wheelSerialNumber) > 0;
//    }

//    default boolean existByWheelSerialNumber(String wheelSerialNumber) {
//        return countByWheelSerialNumber(wheelSerialNumber) > 0;
//    }
}
