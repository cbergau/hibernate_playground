package de.christianbergau.hibernate.userguide;

import de.christianbergau.hibernate.userguide.entity.*;
import de.christianbergau.hibernate.userguide.typecontributor.BitSetType;
import jdk.vm.ci.meta.Local;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.spi.MetamodelImplementor;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.time.LocalDateTime;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;

public class App {
    private static SessionFactory sessionFactory;

    public static void main(String... args) {
        // Allow to persist java.util.BitSet, by registering our own Type Contributor
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().build();
        MetadataSources sources = new MetadataSources(serviceRegistry);
        MetadataBuilder metadataBuilder = sources.getMetadataBuilder();
        metadataBuilder.applyBasicType(BitSetType.INSTANCE);

        sessionFactory = new Configuration().configure().buildSessionFactory();

        enums();
        attributeConverter();
        attributeConverterWithEntity();
        printPhotoUsingAttributeConverterWithEntity();
        printPhotoUsingAttributeConverterWithEntityParameter();
        readProduct();
        writeProduct();
        readProduct();
    }

    private static void writeProduct() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Product product = new Product();
        product.setId(1);
        product.setDescription("안녕하세요");
        product.setWarranty("My Warranty");
        product.setBitSet(BitSet.valueOf(new byte[]{1, 2, 3}));
        product.setAddedOn(new Date());
        product.setLastUpdated(LocalDateTime.now());

        session.saveOrUpdate(product);

        transaction.commit();
    }

    private static void readProduct() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Product product = session
                .createQuery("SELECT p from Product p WHERE id = :id", Product.class)
                .setParameter("id", 1)
                .getSingleResult();

        System.out.println(product);

        transaction.commit();
    }

    private static void printPhotoUsingAttributeConverterWithEntityParameter() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        MetamodelImplementor metamodelImplementor = (MetamodelImplementor) sessionFactory.getMetamodel();
        Type captionType = metamodelImplementor
                .entityPersister(Photo.class.getName())
                .getPropertyType("caption");

        String queryString = "select p " +
                "from Photo p " +
                "where upper(caption) = upper(:caption) ";
        Photo photo = (Photo) session.createQuery(queryString, Photo.class)
                .unwrap(Query.class)
                .setParameter("caption", new Caption("My Holidays 2020"), captionType)
                .getSingleResult();
        System.out.println(photo);

        transaction.commit();
    }

    private static void printPhotoUsingAttributeConverterWithEntity() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "select p " +
                "from Photo p " +
                "where upper(caption) = upper(:caption) ";
        Photo photo = session.createQuery(queryString, Photo.class)
                .setParameter("caption", "My Holidays 2020")
                .getSingleResult();
        System.out.println(photo);
        transaction.commit();
    }

    private static void attributeConverterWithEntity() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Photo photo = new Photo();
        photo.setId(1);
        photo.setCaption(new Caption("My Holidays 2020"));
        photo.setName("2020");
        session.saveOrUpdate(photo);
        transaction.commit();
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
}
