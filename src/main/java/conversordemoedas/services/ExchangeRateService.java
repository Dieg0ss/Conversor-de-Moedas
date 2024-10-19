package main.java.conversordemoedas.services;

import main.java.conversordemoedas.exceptions.CurrencyNotSupportedException;
import main.java.conversordemoedas.models.ExchangeRate;
import main.java.conversordemoedas.repositories.ExchangeRateRepository;

import java.util.Set;

public class ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;
    private final Set<String> supportedCurrencies;
    private final ConversionHistory conversionHistory;

    public ExchangeRateService() {
        this.exchangeRateRepository = new ExchangeRateRepository();
        this.supportedCurrencies = exchangeRateRepository.getSupportedCurrencies();
        this.conversionHistory = new ConversionHistory();
    }
    public double conversionCurrency(double amountToConvert, String fromCurrency, String toCurrency) {

        boolean isFromCurrencySupported = supportedCurrencies.contains(fromCurrency);
        boolean isToCurrencySupported = supportedCurrencies.contains(toCurrency);

        if(!isFromCurrencySupported && !isToCurrencySupported){
            throw new CurrencyNotSupportedException("Moedas inválidas: " + fromCurrency + " - " + toCurrency);
        } else if (!isFromCurrencySupported) {
            throw new CurrencyNotSupportedException("Moeda de origem inválida: " + fromCurrency);
        } else if (!isToCurrencySupported){
            throw new CurrencyNotSupportedException("Moeda de destino inválida: " + toCurrency);
        }

        ExchangeRate exchangeRate = exchangeRateRepository.getExchangeRate(fromCurrency, toCurrency);
        double convertedAmount = amountToConvert * exchangeRate.getRate();
        conversionHistory.addConversion(fromCurrency, toCurrency, amountToConvert, convertedAmount, exchangeRate.getRate());
        return convertedAmount;
    }

    public Set<String> getSupportedCurrencies() {
        return supportedCurrencies;
    }

    public ConversionHistory getConversionHistory() {
        return conversionHistory;
    }
}
