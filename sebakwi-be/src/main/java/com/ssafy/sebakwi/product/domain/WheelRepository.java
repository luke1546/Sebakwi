package com.ssafy.sebakwi.product.domain;

import com.ssafy.sebakwi.product.dto.WheelMonthlyStatus;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface WheelRepository extends JpaRepository<Wheel, Integer> {

    @Query("SELECT w FROM Wheel w WHERE w.serialNumber = :wheelSerialNumber")
    Wheel findByWheelSerialNumber(String wheelSerialNumber);

    /**
     * 메인페이지
     */

    @Query("SELECT w, w.serialNumber, w.oht.serialNumber, w.position, c1.crack, c1.stamp, c1.peeling FROM Wheel w " +
            "LEFT JOIN FETCH w.checkupLists c1 " +
            "WHERE c1.id = (SELECT MAX(c2.id) FROM CheckupList c2 WHERE c2.wheel.id = w.id AND c2.checkedDate >= :startOfMonth) " +
            "AND (c1.crack = true or c1.stamp = true or c1.peeling = true)")
    List<Object[]> findWheelMonthlyStatus(LocalDateTime startOfMonth);

}
