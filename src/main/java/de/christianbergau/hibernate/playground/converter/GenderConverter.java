package de.christianbergau.hibernate.playground.converter;

import de.christianbergau.hibernate.playground.entity.Gender;

import javax.persistence.AttributeConverter;

public class GenderConverter implements AttributeConverter<Gender, Character> {
    @Override
    public Character convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }

        return gender.getCode();
    }

    @Override
    public Gender convertToEntityAttribute(Character character) {
        if (character == null) {
            return null;
        }

        return Gender.fromCode(character);
    }
}
