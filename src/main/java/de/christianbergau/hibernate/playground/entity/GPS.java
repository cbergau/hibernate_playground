package de.christianbergau.hibernate.playground.entity;

import lombok.AllArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@Embeddable
public class GPS implements Coordinates {
    private Double latitude;
    private Double longitude;

    private GPS() {}

    @Override
    public double x() {
        return latitude;
    }

    @Override
    public double y() {
        return longitude;
    }
}
