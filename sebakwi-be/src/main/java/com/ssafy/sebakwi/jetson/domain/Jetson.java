package com.ssafy.sebakwi.jetson.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Jetson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jetson_id")
    private int id;

    private boolean foupstocker;
    private boolean etching;
    private boolean cleaning;
    private boolean photo;

    public void updateFoupstocker(boolean foupstocker) {
        this.foupstocker = foupstocker;
    }

    public void updateEtching(boolean etching) {
        this.etching = etching;
    }

    public void updateCleaning(boolean cleaning) {
        this.cleaning = cleaning;
    }

    public void updatePhoto(boolean photo) {
        this.photo = photo;
    }

}
