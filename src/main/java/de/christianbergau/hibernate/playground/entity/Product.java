package de.christianbergau.hibernate.playground.entity;

import de.christianbergau.hibernate.playground.generators.FunctionCreationTimestamp;
import de.christianbergau.hibernate.playground.typecontributor.BitSetType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.BitSet;

@Entity(name = "Product")
@Table(name = "Product")
@TypeDef(
        name = "bitset",
        defaultForType = BitSet.class,
        typeClass = BitSetType.class
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private Integer id;

    @Type(type = "bitset")
    @Column(name = "bitset")
    private BitSet bitSet;

    @Lob
    private String warranty;

    @Nationalized
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date addedOn;

    /**
     * Because the mapping between the Java 8 Date/Time classes and the SQL types is implicit,
     * there is not need to specify the @Temporal annotation.
     */
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @FunctionCreationTimestamp
    private Date anotherLastUpdated;
}
