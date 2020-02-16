package de.christianbergau.hibernate.userguide.entity;

import de.christianbergau.hibernate.userguide.typecontributor.BitSetType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
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
}
