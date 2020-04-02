package de.christianbergau.hibernate.playground.chapter10locking;

import lombok.Data;
import org.hibernate.annotations.OptimisticLock;

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

    @OptimisticLock(excluded = true)
    private long callCount;

    public void incrementCallCount() {
        callCount++;
    }
}
