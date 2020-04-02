package de.christianbergau.hibernate.playground.chapter10locking;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "OptimisticLockedPerson")
@Data
public class OptimisticLockedPerson {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "`name`")
    private String name;

    @Version
    private long version;
}
