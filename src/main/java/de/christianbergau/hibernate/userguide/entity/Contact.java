package de.christianbergau.hibernate.userguide.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URL;

@Entity(name = "Contact")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contact {
    @Id
    private Integer id;
    private Name name;

    @Column(name = "notes")
    @Type(type = "materialized_nclob")
    private String notes;
    private URL website;
    private boolean starred;
}
