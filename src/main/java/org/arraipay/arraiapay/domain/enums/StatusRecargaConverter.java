package org.arraipay.arraiapay.domain.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StatusRecargaConverter implements AttributeConverter<StatusRecarga, String> {
    @Override public String convertToDatabaseColumn(StatusRecarga value) { return EnumDatabaseValues.toDatabase(value); }
    @Override public StatusRecarga convertToEntityAttribute(String value) { return EnumDatabaseValues.fromDatabase(value, StatusRecarga.class); }
}
