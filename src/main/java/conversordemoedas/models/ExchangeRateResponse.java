package main.java.conversordemoedas.models;

public record ExchangeRateResponse(double conversion_rate, String base_code, String target_code) {
}
