package org.arraipay.arraiapay.domain.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FormaPagamentoConverter implements AttributeConverter<FormaPagamento, String> {
    @Override public String convertToDatabaseColumn(FormaPagamento value) { return EnumDatabaseValues.toDatabase(value); }
    @Override public FormaPagamento convertToEntityAttribute(String value) { return EnumDatabaseValues.fromDatabase(value, FormaPagamento.class); }
}
