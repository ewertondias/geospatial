package com.sccon.geospatial.infrastructure.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public final class CurrencyUtils {

    private static final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));

    private CurrencyUtils() {
    }

    public static String toBRL(BigDecimal value) {
        return numberFormat.format(value);
    }

}
