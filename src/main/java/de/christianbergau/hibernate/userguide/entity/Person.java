package de.christianbergau.hibernate.userguide.entity;

import de.christianbergau.hibernate.userguide.converter.GenderConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    private Integer id;

    private String name;

    @Convert(converter = GenderConverter.class)
    public Gender gender;
}
