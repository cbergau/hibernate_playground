package de.christianbergau.hibernate.userguide;

import de.christianbergau.hibernate.userguide.entity.Product;
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

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = new Product(1, BitSet.valueOf(new byte[]{1, 2, 3}));
        session.save(product);
        transaction.commit();


    }
}
