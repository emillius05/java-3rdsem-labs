package org.example.locale.model;

import java.math.BigDecimal;
import java.util.Locale;

public record LocaleNumberEntry(Locale locale, String rawNumber, BigDecimal value) {
}
