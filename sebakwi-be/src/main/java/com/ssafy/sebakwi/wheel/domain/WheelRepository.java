package com.ssafy.sebakwi.wheel.domain;

import com.ssafy.sebakwi.checkupList.dto.CheckupListDetailModalDto;
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


    @Query("SELECT w FROM Wheel w WHERE w.createdDate < :limitDate")
    List<Wheel> findWheelReplacement(LocalDate limitDate);


    /**
     * checkupList 상세모달
     */
    @Query("SELECT w FROM Wheel w WHERE w.oht.serialNumber = :ohtNumber ORDER BY w.position")
    List<Wheel> findWheelByOhtNumber(String ohtNumber);

//    @Query("SELECT NEW com.ssafy.sebakwi.checkupList.dto.CheckupListDetailModalDto(w, c) " +
//            "FROM Wheel w " +
//            "LEFT JOIN w.checkupLists c " +
//            "WHERE w.oht.serialNumber = :ohtNumber AND " +
//            "c.checkedDate BETWEEN :findStartDateTime AND :findEndDateTime " +
//            "GROUP BY w.id, c.id " +
//            "HAVING c.id = (SELECT MAX(c1.id) " +
//            "FROM CheckupList c1 " +
//            "WHERE c1.wheel = w) " +
//            "ORDER BY w.position")
    @Query("SELECT NEW com.ssafy.sebakwi.checkupList.dto.CheckupListDetailModalDto(w, c) " +
            "FROM Wheel w " +
            "LEFT JOIN w.checkupLists c " +
            "WHERE w.oht.serialNumber = :ohtNumber AND " +
            "c.checkedDate <= :findEndDateTime " +
            "GROUP BY w.id, c.id " +
            "HAVING c.id = (SELECT MAX(c1.id) " +
            "FROM CheckupList c1 " +
            "WHERE c1.wheel = w AND " +
            "c.checkedDate <= :findEndDateTime) " +
            "ORDER BY w.position")
    List<CheckupListDetailModalDto> findOtherWheelDetailByWheelNumber(String ohtNumber, LocalDateTime findEndDateTime);
}
