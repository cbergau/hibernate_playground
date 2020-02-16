package de.christianbergau.hibernate.userguide.entity;

import de.christianbergau.hibernate.userguide.converter.GenderConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
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

    private String firstName;

    private String lastName;

    private String middleName1;

    private String middleName2;

    private String middleName3;

    private String middleName4;

    private String middleName5;

    @Generated(value = GenerationTime.ALWAYS)
    @Column(columnDefinition =
            "AS CONCAT(" +
                    "	COALESCE(firstName, ''), " +
                    "	COALESCE(' ' + middleName1, ''), " +
                    "	COALESCE(' ' + middleName2, ''), " +
                    "	COALESCE(' ' + middleName3, ''), " +
                    "	COALESCE(' ' + middleName4, ''), " +
                    "	COALESCE(' ' + middleName5, ''), " +
                    "	COALESCE(' ' + lastName, '') " +
                    ")")
    private String fullName;

    @Convert(converter = GenderConverter.class)
    public Gender gender;
}
