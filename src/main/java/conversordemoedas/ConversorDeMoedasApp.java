package main.java.conversordemoedas;

import main.java.conversordemoedas.controllers.ExchangeRateController;

public class ConversorDeMoedasApp {
    public static void main(String[] args) {
        ExchangeRateController exchangeRateController = new ExchangeRateController();
        exchangeRateController.showMenu();
    }
}
