package de.christianbergau.hibernate.playground.entity;

import lombok.Data;
import org.hibernate.annotations.Target;

import javax.persistence.*;

@Data
@Entity(name = "City")
@Table(name = "City")
public class City {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Embedded
    @Target(GPS.class)
    private Coordinates coordinates;
}
