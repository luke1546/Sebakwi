package com.ssafy.sebakwi.product.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface CheckupListRepository extends JpaRepository<CheckupList, Integer> {

    @Query("SELECT c FROM CheckupList c WHERE c.status = :status AND c.checkedDate <= :endDateTime ORDER BY c.checkedDate DESC")
    Page<CheckupList> findStatusCheckupListArray(LocalDateTime endDateTime, Enum status, Pageable pageable);

    @Query("SELECT c FROM CheckupList c WHERE c.status = :status AND c.checkedDate >= :startDateTime AND c.checkedDate <= :endDateTime ORDER BY c.checkedDate DESC")
    Page<CheckupList> findStartDateTimeStatusCheckupListArray(LocalDateTime startDateTime,
                                                              LocalDateTime endDateTime,
                                                              Enum status, Pageable pageable);

    @Query("SELECT c FROM CheckupList c WHERE c.checkedDate <= :endDateTime ORDER BY c.checkedDate DESC")
    Page<CheckupList> findCheckupListArray(LocalDateTime endDateTime, Pageable pageable);

    @Query("SELECT c FROM CheckupList c WHERE c.checkedDate >= :startDateTime AND c.checkedDate <= :endDateTime ORDER BY c.checkedDate DESC")
    Page<CheckupList> findStartDateTimeCheckupListArray(LocalDateTime startDateTime,
                                                        LocalDateTime endDateTime,
                                                        Pageable pageable);
}
