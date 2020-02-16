package de.christianbergau.hibernate.userguide.converter;

import de.christianbergau.hibernate.userguide.entity.Caption;

import javax.persistence.AttributeConverter;

public class CaptionConverter implements AttributeConverter<Caption, String> {

    @Override
    public String convertToDatabaseColumn(Caption attribute) {
        return attribute.getText();
    }

    @Override
    public Caption convertToEntityAttribute(String dbData) {
        return new Caption(dbData);
    }
}
