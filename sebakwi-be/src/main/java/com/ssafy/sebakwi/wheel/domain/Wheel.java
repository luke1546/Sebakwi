package com.ssafy.sebakwi.wheel.domain;

import com.ssafy.sebakwi.checkupList.domain.CheckupList;
import com.ssafy.sebakwi.oht.domain.Oht;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Wheel {

    @Id @GeneratedValue
    @Column(name = "wheel_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oht_id")
    private Oht oht;

    @Column(length = 20, name = "serial_number")
    private String serialNumber;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(length = 20)
    private int position;

    @OneToMany(mappedBy = "wheel", cascade = CascadeType.ALL)
    @Builder.Default // 혹시 안되면 지움
    private List<CheckupList> checkupLists = new ArrayList<>();

    public void changeCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
