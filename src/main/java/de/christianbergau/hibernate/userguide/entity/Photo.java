package de.christianbergau.hibernate.userguide.entity;

import de.christianbergau.hibernate.userguide.converter.CaptionConverter;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Photo")
@Data
@ToString
public class Photo {

    @Id
    private Integer id;

    private String name;

    @Convert(converter = CaptionConverter.class)
    private Caption caption;
}
