package com.ssafy.sebakwi.product.domain;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OhtRepository extends JpaRepository<Oht, Long> {
    @Query("SELECT o FROM Oht o WHERE o.serial_number = :oht_serial_number")
    Oht findByOhtSerialNumber(@Param("oht_serial_number")String ohtSerialNumber);
}
