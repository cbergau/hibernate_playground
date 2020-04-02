package de.christianbergau.hibernate.playground.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity(name = "OptimisticLockedEntityWithVersionOfTimestamp")
@Table(name = "OptimisticLockedEntityWithVersionOfTimestamp")
@Data
public class OptimisticLockedEntityWithVersionOfTimestamp {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Timestamp version;

    @Column(name = "value")
    private String value;
}
