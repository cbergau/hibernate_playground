package de.christianbergau.hibernate.playground.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "OptimisticLockedEntityWithVersionOfInstant")
@Table(name = "OptimisticLockedEntityWithVersionOfInstant")
@Data
public class OptimisticLockedEntityWithVersionOfInstant {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Instant version;

    @Column(name = "value")
    private String value;
}
