package de.christianbergau.hibernate.userguide.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Phone")
@Table(name = "Phone")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    @Id
    private Integer id;
    private String number;

    @Enumerated(EnumType.STRING)
    private PhoneType type;
}
