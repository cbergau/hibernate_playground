package de.christianbergau.hibernate.playground.chapter10locking;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Demonstration of Optimistic and Pessimistic Locking with Hibernate 5.4
 *
 * @see "https://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html#locking"
 */
@Log4j2
public class LockingWithExcludedFieldApp {
    private static SessionFactory sessionFactory;

    public static void main(String... args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        OptimisticLockedPerson person = new OptimisticLockedPerson();
        person.setName("Chris");
        person.setCallCount(0);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(person);
        transaction.commit();
        session.close();

        // We saved it, so now, 2 different transaction are changing the name of the entity
        // and also incrementing the counter.
        Session session1 = sessionFactory.openSession();
        Transaction transaction1 = session1.beginTransaction();
        OptimisticLockedPerson person1 = session1.find(OptimisticLockedPerson.class, person.getId());
        person1.incrementCallCount();
        session1.saveOrUpdate(person1);

        // Start a second Session, and also increment the counter
        Session session2 = sessionFactory.openSession();
        Transaction transaction2 = session2.beginTransaction();
        OptimisticLockedPerson person2 = session2.find(OptimisticLockedPerson.class, person.getId());
        person2.incrementCallCount();
        session2.saveOrUpdate(person2);
        transaction2.commit();
        session2.close();
        // end of second Session


        // if we would not have excluded callCount with @OptimisticLock(excluded = true) then we would get:

        // javax.persistence.OptimisticLockException:
        //  Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect)
        transaction1.commit();
        session1.close();

        // In a third Session we retrieve the Entity and check the counter
        Session session3 = sessionFactory.openSession();
        OptimisticLockedPerson person3 = session3.find(OptimisticLockedPerson.class, person.getId());

        // Final result of Person OptimisticLockedPerson(id=1, name=Chris, version=0, callCount=1)
        log.info("Final result of Person {}", person3);
        session3.close();
    }
}
