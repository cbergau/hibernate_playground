package de.christianbergau.hibernate.playground.chapter10locking;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class PessimisticLockedPerson {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
}
