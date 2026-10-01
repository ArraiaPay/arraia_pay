package org.arraipay.arraiapay.domain.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StatusCartaoConverter implements AttributeConverter<StatusCartao, String> {
    @Override public String convertToDatabaseColumn(StatusCartao value) { return EnumDatabaseValues.toDatabase(value); }
    @Override public StatusCartao convertToEntityAttribute(String value) { return EnumDatabaseValues.fromDatabase(value, StatusCartao.class); }
}
