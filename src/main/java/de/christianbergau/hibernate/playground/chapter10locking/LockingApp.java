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
public class LockingApp {
    private static SessionFactory sessionFactory;

    public static void main(String... args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        OptimisticLockedPerson person = new OptimisticLockedPerson();
        person.setName("Chris");

        // Person before saving: OptimisticLockedPerson(id=null, name=Chris, version=0)
        log.info("Person before saving: {}", person);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(person);

        // Person after saving: OptimisticLockedPerson(id=1, name=Chris, version=0)
        log.info("Person after saving: {}", person);

        person.setName("Christian");
        session.saveOrUpdate(person);

        // Person after update: OptimisticLockedPerson(id=1, name=Christian, version=0)
        log.info("Person after update: {}", person);

        // After flushing (transaction.commit()) the entity gets updated, and the version is increased
        transaction.commit();

        // Person after commit: OptimisticLockedPerson(id=1, name=Christian, version=1)
        log.info("Person after commit: {}", person);

        session.close();
    }
}
