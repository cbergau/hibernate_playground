package de.christianbergau.hibernate.userguide;

import de.christianbergau.hibernate.userguide.entity.*;
import de.christianbergau.hibernate.userguide.typecontributor.BitSetType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.BitSet;

public class App {
    private static SessionFactory sessionFactory;

    public static void main(String... args) {
        // Allow to persist java.util.BitSet, by registering our own Type Contributor
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().build();
        MetadataSources sources = new MetadataSources(serviceRegistry);
        MetadataBuilder metadataBuilder = sources.getMetadataBuilder();
        metadataBuilder.applyBasicType(BitSetType.INSTANCE);

        sessionFactory = new Configuration().configure().buildSessionFactory();

        customTypes();
        enums();
        attributeConverter();
    }

    private static void attributeConverter() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Person aMan = new Person(1, "Christian", Gender.MALE);
        Person aWoman = new Person(2, "Christiane", Gender.FEMALE);
        session.saveOrUpdate(aMan);
        session.saveOrUpdate(aWoman);
        transaction.commit();
    }

    private static void enums() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Phone phone = new Phone(1, "123-456", PhoneType.MOBILE);
        session.saveOrUpdate(phone);
        transaction.commit();
    }

    private static void customTypes() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = new Product(1, BitSet.valueOf(new byte[]{1, 2, 3}));
        session.saveOrUpdate(product);
        transaction.commit();
    }
}
