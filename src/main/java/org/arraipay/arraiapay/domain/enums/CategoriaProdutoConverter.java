package org.arraipay.arraiapay.domain.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CategoriaProdutoConverter implements AttributeConverter<CategoriaProduto, String> {
    @Override public String convertToDatabaseColumn(CategoriaProduto value) { return EnumDatabaseValues.toDatabase(value); }
    @Override public CategoriaProduto convertToEntityAttribute(String value) { return EnumDatabaseValues.fromDatabase(value, CategoriaProduto.class); }
}
