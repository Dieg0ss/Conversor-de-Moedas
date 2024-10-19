package main.java.conversordemoedas.services;

import main.java.conversordemoedas.models.ConversionRecord;

import java.util.ArrayList;
import java.util.List;

public class ConversionHistory {

    private final List<ConversionRecord> conversionRecordList;

    public ConversionHistory(){
        this.conversionRecordList = new ArrayList<>();
    }

    public void addConversion(String fromCurrency, String toCurrency, double amountConverted, double convertedAmount, double rate){
        ConversionRecord record = new ConversionRecord(fromCurrency, toCurrency, amountConverted, convertedAmount, rate);
        conversionRecordList.add(record);
    }

    public List<ConversionRecord> getConversionRecordList() {
        return conversionRecordList;
    }
}
