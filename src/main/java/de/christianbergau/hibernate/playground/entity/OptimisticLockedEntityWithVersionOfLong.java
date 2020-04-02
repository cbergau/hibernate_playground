package de.christianbergau.hibernate.playground.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "OptimisticLockedEntityWithVersionOfLong")
@Table(name = "OptimisticLockedEntityWithVersionOfLong")
@Data
public class OptimisticLockedEntityWithVersionOfLong {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Column(name = "value")
    private String value;
}
