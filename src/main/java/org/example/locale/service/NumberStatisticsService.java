package org.example.locale.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.locale.exception.InvalidLocaleNumberException;
import org.example.locale.model.LocaleNumberEntry;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class NumberStatisticsService {
    private static final Logger logger = LogManager.getLogger(NumberStatisticsService.class);

    public BigDecimal calculateSum(List<LocaleNumberEntry> entries) throws InvalidLocaleNumberException {
        if (entries == null || entries.isEmpty()) {
            throw new InvalidLocaleNumberException("Список чисел пуст, сумму вычислить нельзя");
        }
        BigDecimal sum = BigDecimal.ZERO;
        for (LocaleNumberEntry entry : entries) {
            sum = sum.add(entry.value());
        }
        logger.info("Вычислена сумма {} чисел: {}", entries.size(), sum);
        return sum;
    }

    public BigDecimal calculateAverage(List<LocaleNumberEntry> entries) throws InvalidLocaleNumberException {
        BigDecimal sum = calculateSum(entries);
        BigDecimal avg = sum.divide(BigDecimal.valueOf(entries.size()), MathContext.DECIMAL128)
                .setScale(6, RoundingMode.HALF_UP)
                .stripTrailingZeros();
        logger.info("Вычислено среднее значение: {}", avg);
        return avg;
    }
}
