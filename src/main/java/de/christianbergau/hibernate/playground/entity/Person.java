package de.christianbergau.hibernate.playground.entity;

import de.christianbergau.hibernate.playground.converter.GenderConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
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

    @ColumnTransformer(
            read = "AES_DECRYPT(password, 'mykey')",
            write = "AES_ENCRYPT(?, 'mykey')"
    )
    private byte[] password;

    @Convert(converter = GenderConverter.class)
    public Gender gender;
}
