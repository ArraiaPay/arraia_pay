package org.arraipay.arraiapay.domain.enums;

import java.util.Locale;

final class EnumDatabaseValues {
    private EnumDatabaseValues() { }

    static String toDatabase(Enum<?> value) {
        return value == null ? null : value.name().toLowerCase(Locale.ROOT);
    }

    static <E extends Enum<E>> E fromDatabase(String value, Class<E> enumType) {
        return value == null ? null : Enum.valueOf(enumType, value.toUpperCase(Locale.ROOT));
    }
}
