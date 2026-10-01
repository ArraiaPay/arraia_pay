package org.arraipay.arraiapay.domain.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PerfilUsuarioConverter implements AttributeConverter<PerfilUsuario, String> {
    @Override public String convertToDatabaseColumn(PerfilUsuario value) { return EnumDatabaseValues.toDatabase(value); }
    @Override public PerfilUsuario convertToEntityAttribute(String value) { return EnumDatabaseValues.fromDatabase(value, PerfilUsuario.class); }
}
