package main.java.conversordemoedas.models;

public class ExchangeRate {
    private String fromCurrency;
    private String toCurrency;
    private double rate;

    public ExchangeRate(){
    }

    public ExchangeRate(ExchangeRateResponse exchangeRateResponse){
        this.fromCurrency = exchangeRateResponse.base_code();
        this.toCurrency = exchangeRateResponse.target_code();
        this.rate = exchangeRateResponse.conversion_rate();
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public double getRate() {
        return rate;
    }

}
