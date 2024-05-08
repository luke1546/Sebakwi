package com.ssafy.sebakwi.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OhtRepository extends JpaRepository<Oht, Long> {
    @Query("SELECT o FROM Oht o WHERE o.serialNumber = :serialNumber")
    Oht findByOhtSerialNumber(String serialNumber);

    @Query("SELECT COUNT(o) FROM Oht o")
    int countOht();

    @Query("SELECT COUNT(*) FROM Oht o WHERE o.maintenance = true")
    int countMaintenance();
}
