package com.ssafy.sebakwi.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CheckupListRepository extends JpaRepository<CheckupList, Integer> {

//    @Query("SELECT c FROM CheckupList c WHERE c.id = :checkupListId")
//    CheckupList findByCheckupListId(int checkupListId);
}
