package main.java.conversordemoedas.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversionRecord {

    private final String fromCurrency;
    private final String toCurrency;
    private final double amountToConvert;
    private final double convertedAmount;
    private final double conversionRate;
    private final LocalDateTime timeInstant;

    public ConversionRecord(String fromCurrency, String toCurrency, double amountToConvert, double convertedAmount, double conversionRate) {

        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amountToConvert = amountToConvert;
        this.convertedAmount = convertedAmount;
        this.conversionRate = conversionRate;
        this.timeInstant = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("Conversão: %s -> %s | Valor: %.2f | Resultado: %.2f | Taxa: %.4f | Hora: %s",
                fromCurrency, toCurrency, amountToConvert, convertedAmount, conversionRate, timeInstant.format(dateTimeFormatter));
    }
}
