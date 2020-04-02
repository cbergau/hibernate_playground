package de.christianbergau.hibernate.playground.chapter10locking;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.LockModeType;
import java.util.Collections;

/**
 * Demonstration of Pessimistic Locking with Hibernate 5.4
 *
 * @see "https://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html#locking"
 */
@Log4j2
public class PessimisticLockingApp {
    public static void main(String... args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        PessimisticLockedPerson person = new PessimisticLockedPerson();
        person.setFirstName("Chris");

        save(sessionFactory, person);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // SQL by Hibernate Driver:

        // SELECT
        //      pessimisti0_.id as id1_6_0_,
        //      pessimisti0_.firstName as firstnam2_6_0_
        // FROM
        //      PessimisticLockedPerson pessimisti0_
        // WHERE
        //      pessimisti0_.id=1
        // FOR UPDATE
        PessimisticLockedPerson fromDb = session.find(
                PessimisticLockedPerson.class,
                person.getId(),
                LockModeType.PESSIMISTIC_WRITE
        );

        // Person from db PessimisticLockedPerson(id=1, firstName=Chris)
        log.info("Person from db {}", fromDb);

        transaction.commit();
        session.close();
    }

    private static void save(SessionFactory sessionFactory, PessimisticLockedPerson person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(person);
        transaction.commit();
        session.close();
    }
}
