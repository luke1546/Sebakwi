package com.ssafy.sebakwi.product.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Alarm {

    @Id @GeneratedValue
    @Column(name = "alarm_id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "checkup_list_id")
    private CheckupList checkupList;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
