package main.java.conversordemoedas.controllers;

import main.java.conversordemoedas.exceptions.CurrencyNotSupportedException;
import main.java.conversordemoedas.models.ConversionRecord;
import main.java.conversordemoedas.services.ExchangeRateService;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController() {
        this.exchangeRateService = new ExchangeRateService();
    }

    public void showMenu() {

        Scanner scanner = new Scanner(System.in);
        int userOption = -1;
        String mainMenu = """
           ==================================================================================================================
                                                          Conversor de Moedas                                              \s
           \s
                                                      Desenvolvido por Diego Sousa                                         \s
           ==================================================================================================================
           Opções:                              \s
           \s
           1 - Conversão de moedas              \s
           2 - Moedas disponíveis para conversão\s
           3 - Históricos de conversões         \s
           0 - Sair                             \s
           ==================================================================================================================
           Selecione uma opção numérica:\s""";

        while (userOption != 0){
            System.out.print(mainMenu);

            try {
                userOption = scanner.nextInt();

                switch (userOption) {
                    case 1 -> conversionCurrencyScanner(scanner);
                    case 2 -> showSupportedCurrencies();
                    case 3 -> showConversionHistory();
                    case 0 -> System.out.print("Programa encerrado.");
                    default -> System.err.println("Opção inválida! Digite novamente.");
                }
            } catch (InputMismatchException e){
                System.err.println("Erro: Por favor, insira um número válido.");
                scanner.nextLine();
                System.out.println();
            }
            System.out.println();
        }
    }

    public void conversionCurrencyScanner(Scanner scanner) {

        System.out.print("Digite a primeira moeda a ser convertida: ");
        String fromCurrency = scanner.next().toUpperCase();
        System.out.print("Digite a outra moeda: ");
        String toCurrency = scanner.next().toUpperCase();

        System.out.print("Digite o valor a ser convertido: ");
        double amountToConvert = scanner.nextDouble();

        try {
            double convertedAmount = exchangeRateService.conversionCurrency(amountToConvert, fromCurrency, toCurrency);
            System.out.printf("O valor de (%s) %.2f corresponde ao valor de (%s) %.2f", fromCurrency, amountToConvert, toCurrency, convertedAmount);
        } catch (CurrencyNotSupportedException e){
            System.err.println("Erro: " + e.getMessage());
        } catch (Exception e){
            System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public void showSupportedCurrencies(){

        int count = 0;
        int columns = 12;

        String supportedCurrenciesMenu = """
        ==================================================================================================================
                                                Moedas disponíveis para conversão:
        ==================================================================================================================""";
        System.out.println(supportedCurrenciesMenu);

        Set<String> supportedCurrencies = exchangeRateService.getSupportedCurrencies();
        for (String currency : supportedCurrencies){
            System.out.printf("%-10s", currency);
            count++;
            if (count % columns == 0){
                System.out.println();
            }
        }

        if (count % columns != 0) {
            System.out.println();
        }

        System.out.println("==================================================================================================================");

    }

    public void showConversionHistory() {

        System.out.println();
        System.out.println("Histórico de conversões:");
        for (ConversionRecord record : exchangeRateService.getConversionHistory().getConversionRecordList()){
            System.out.println(record);
        }
    }
}
