package org.arraipay.arraiapay.domain.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StatusVendaConverter implements AttributeConverter<StatusVenda, String> {
    @Override public String convertToDatabaseColumn(StatusVenda value) { return EnumDatabaseValues.toDatabase(value); }
    @Override public StatusVenda convertToEntityAttribute(String value) { return EnumDatabaseValues.fromDatabase(value, StatusVenda.class); }
}
