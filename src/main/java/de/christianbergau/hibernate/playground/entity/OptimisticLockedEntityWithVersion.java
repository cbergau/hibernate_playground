package de.christianbergau.hibernate.playground.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "OptimisticLockedEntityWithVersion")
@Table(name = "OptimisticLockedEntityWithVersion")
@Data
public class OptimisticLockedEntityWithVersion {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Column(name = "value")
    private String value;
}
