package com.ssafy.sebakwi.product.domain;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface CheckupListRepository extends JpaRepository<CheckupList, Integer> {

    // 검진일시
    @Query("SELECT c FROM CheckupList c WHERE c.status = :status AND " +
            "(:ohtSerialNumber = '' or c.wheel.oht.serialNumber = :ohtSerialNumber) AND " +
            "(:isCheckedDate != true or c.checkedDate <= :endDateTime) AND " +
            "(:isCheckedDate = true or c.wheel.createdDate <= DATE_TRUNC('day', CAST(:endDateTime AS DATE))) AND " +
            "(:position = 0 or c.wheel.position = :position) " +
            "ORDER BY " +
            "CASE WHEN :isSortByCheck = true AND :isDesc = true THEN c.checkedDate END DESC, " +
            "CASE WHEN :isSortByCheck = true AND :isDesc != true THEN c.checkedDate END ASC, " +
            "CASE WHEN :isSortByCheck != true AND :isDesc = true THEN c.wheel.createdDate END DESC, " +
            "CASE WHEN :isSortByCheck != true AND :isDesc != true THEN c.wheel.createdDate END ASC "
    )
    Page<CheckupList> findStatusCheckupListArray(@Param("isCheckedDate") boolean isCheckedDate,
                                                 @Param("endDateTime") LocalDateTime endDateTime,
                                                 @Param("status") WheelStatus status,
                                                 Pageable pageable,
                                                 @Param("isSortByCheck") boolean isSortByCheck,
                                                 @Param("isDesc") boolean isDesc,
                                                 @Param("position") int position,
                                                 @Param("ohtSerialNumber") String ohtSerialNumber);

    @Query("SELECT c FROM CheckupList c WHERE c.status = :status AND " +
            "(:ohtSerialNumber = '' or c.wheel.oht.serialNumber = :ohtSerialNumber) AND " +
            "(:isCheckedDate != true or c.checkedDate >= :startDateTime) AND " +
            "(:isCheckedDate = true or c.wheel.createdDate >= DATE_TRUNC('day', CAST(:startDateTime AS DATE))) AND " +
            "(:isCheckedDate != true or c.checkedDate <= :endDateTime) AND " +
            "(:isCheckedDate = true or c.wheel.createdDate <= DATE_TRUNC('day', CAST(:endDateTime AS DATE))) AND" +
            "(:position = 0 or c.wheel.position = :position) " +
            "ORDER BY " +
            "CASE WHEN :isSortByCheck = true AND :isDesc = true THEN c.checkedDate END DESC, " +
            "CASE WHEN :isSortByCheck = true AND :isDesc != true THEN c.checkedDate END ASC, " +
            "CASE WHEN :isSortByCheck != true AND :isDesc = true THEN c.wheel.createdDate END DESC, " +
            "CASE WHEN :isSortByCheck != true AND :isDesc != true THEN c.wheel.createdDate END ASC ")
    Page<CheckupList> findStartDateTimeStatusCheckupListArray(@Param("isCheckedDate") boolean isCheckedDate,
                                                              @Param("startDateTime") LocalDateTime startDateTime,
                                                              @Param("endDateTime") LocalDateTime endDateTime,
                                                              @Param("pageable") Enum status,
                                                              Pageable pageable,
                                                              @Param("isSortByCheck") boolean isSortByCheck,
                                                              @Param("isDesc") boolean isDesc,
                                                              @Param("position") int position,
                                                              @Param("ohtSerialNumber") String ohtSerialNumber);

    @Query("SELECT c FROM CheckupList c " +
            "WHERE " +
            "(:ohtSerialNumber = '' or c.wheel.oht.serialNumber = :ohtSerialNumber) AND " +
            "(:isCheckedDate != true or c.checkedDate <= :endDateTime) AND " +
            "(:isCheckedDate = true or c.wheel.createdDate <= DATE_TRUNC('day', CAST(:endDateTime AS DATE))) AND" +
            "(:position = 0 or c.wheel.position = :position) " +
            "ORDER BY " +
            "CASE WHEN :isSortByCheck = true AND :isDesc = true THEN c.checkedDate END DESC, " +
            "CASE WHEN :isSortByCheck = true AND :isDesc != true THEN c.checkedDate END ASC, " +
            "CASE WHEN :isSortByCheck != true AND :isDesc = true THEN c.wheel.createdDate END DESC, " +
            "CASE WHEN :isSortByCheck != true AND :isDesc != true THEN c.wheel.createdDate END ASC ")
    Page<CheckupList> findCheckupListArray(@Param("isCheckedDate") boolean isCheckedDate,
                                           @Param("endDateTime") LocalDateTime endDateTime,
                                           Pageable pageable,
                                           @Param("isSortByCheck") boolean isSortByCheck,
                                           @Param("isDesc") boolean isDesc,
                                           @Param("position") int position,
                                           @Param("ohtSerialNumber") String ohtSerialNumber);

    @Query("SELECT c FROM CheckupList c " +
            "WHERE " +
            "(:ohtSerialNumber = '' or c.wheel.oht.serialNumber = :ohtSerialNumber) AND " +
            "(:isCheckedDate != true or c.checkedDate >= :startDateTime) AND " +
            "(:isCheckedDate = true or c.wheel.createdDate >= DATE_TRUNC('day', CAST(:startDateTime AS DATE))) AND " +
            "(:isCheckedDate != true or c.checkedDate <= :endDateTime) AND " +
            "(:isCheckedDate = true or c.wheel.createdDate <= DATE_TRUNC('day', CAST(:endDateTime AS DATE))) AND " +
            "(:position = 0 or c.wheel.position = :position) " +
            "ORDER BY " +
            "CASE WHEN :isSortByCheck = true AND :isDesc = true THEN c.checkedDate END DESC, " +
            "CASE WHEN :isSortByCheck = true AND :isDesc != true THEN c.checkedDate END ASC, " +
            "CASE WHEN :isSortByCheck != true AND :isDesc = true THEN c.wheel.createdDate END DESC, " +
            "CASE WHEN :isSortByCheck != true AND :isDesc != true THEN c.wheel.createdDate END ASC ")
    Page<CheckupList> findStartDateTimeCheckupListArray(@Param("isCheckedDate") boolean isCheckedDate,
                                                        @Param("startDateTime") LocalDateTime startDateTime,
                                                        @Param("endDateTime") LocalDateTime endDateTime,
                                                        Pageable pageable,
                                                        @Param("isSortByCheck") boolean isSortByCheck,
                                                        @Param("isDesc") boolean isDesc,
                                                        @Param("position") int position,
                                                        @Param("ohtSerialNumber") String ohtSerialNumber);

    // 교체일자
//    @Query("SELECT c FROM CheckupList c JOIN c.wheel w ON c.wheel.id = w.id  WHERE c.status = :status AND w.createdDate <= :endDateTime ORDER BY " +
//            "CASE WHEN :isSortByCheck = true AND :isDesc = true THEN c.checkedDate END DESC, " +
//            "CASE WHEN :isSortByCheck = true OR :isDesc != true THEN c.checkedDate END ASC, " +
//            "CASE WHEN :isSortByCheck != true OR :isDesc = true THEN c.wheel.createdDate END DESC, " +
//            "CASE WHEN :isSortByCheck != true OR :isDesc != true THEN c.wheel.createdDate END ASC ")
//    Page<CheckupList> findCreatedStatusCheckupListArray(LocalDateTime endDateTime, Enum status, Pageable pageable, boolean isSortByCheck, boolean isDesc);
//
//    @Query("SELECT c FROM CheckupList c JOIN c.wheel w ON c.wheel.id = w.id WHERE c.status = :status AND w.createdDate >= :startDateTime AND w.createdDate <= :endDateTime ORDER BY " +
//            "CASE WHEN :isSortByCheck = true AND :isDesc = true THEN c.checkedDate END DESC, " +
//            "CASE WHEN :isSortByCheck = true OR :isDesc != true THEN c.checkedDate END ASC, " +
//            "CASE WHEN :isSortByCheck != true OR :isDesc = true THEN c.wheel.createdDate END DESC, " +
//            "CASE WHEN :isSortByCheck != true OR :isDesc != true THEN c.wheel.createdDate END ASC ")
//    Page<CheckupList> findCreatedStartDateTimeStatusCheckupListArray(LocalDateTime startDateTime,
//                                                                  LocalDateTime endDateTime,
//                                                                  Enum status, Pageable pageable,
//                                                                     boolean isSortByCheck, boolean isDesc);
//
//    @Query("SELECT c FROM CheckupList c JOIN c.wheel w ON c.wheel.id = w.id WHERE w.createdDate <= :endDateTime ORDER BY " +
//            "CASE WHEN :isSortByCheck = true AND :isDesc = true THEN c.checkedDate END DESC, " +
//            "CASE WHEN :isSortByCheck = true OR :isDesc != true THEN c.checkedDate END ASC, " +
//            "CASE WHEN :isSortByCheck != true OR :isDesc = true THEN c.wheel.createdDate END DESC, " +
//            "CASE WHEN :isSortByCheck != true OR :isDesc != true THEN c.wheel.createdDate END ASC ")
//    Page<CheckupList> findCreatedCheckupListArray(LocalDateTime endDateTime, Pageable pageable, boolean isSortByCheck, boolean isDesc);
//
//    @Query("SELECT c FROM CheckupList c JOIN c.wheel w ON c.wheel.id = w.id WHERE w.createdDate >= :startDateTime AND w.createdDate <= :endDateTime ORDER BY " +
//            "CASE WHEN :isSortByCheck = true AND :isDesc = true THEN c.checkedDate END DESC, " +
//            "CASE WHEN :isSortByCheck = true OR :isDesc != true THEN c.checkedDate END ASC, " +
//            "CASE WHEN :isSortByCheck != true OR :isDesc = true THEN c.wheel.createdDate END DESC, " +
//            "CASE WHEN :isSortByCheck != true OR :isDesc != true THEN c.wheel.createdDate END ASC ")
//    Page<CheckupList> findCreatedStartDateTimeCheckupListArray(LocalDateTime startDateTime,
//                                                            LocalDateTime endDateTime,
//                                                            Pageable pageable,
//                                                               boolean isSortByCheck, boolean isDesc);
}
