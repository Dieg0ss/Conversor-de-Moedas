package main.java.conversordemoedas.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.conversordemoedas.models.ExchangeRate;
import main.java.conversordemoedas.models.ExchangeRateResponse;
import main.java.conversordemoedas.models.SupportedCurrenciesResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

public class ExchangeRateRepository {

    private final String apiKey = "ab2dd5d6ec539dd6b73a9170";
    private final String apiUrl = "https://v6.exchangerate-api.com/v6/" + apiKey;

    public ExchangeRate getExchangeRate(String fromCurrency, String toCurrency) {

        String apiUrlRate= apiUrl+"/pair/"+fromCurrency+"/"+toCurrency;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrlRate))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            Gson gson = new GsonBuilder().create();
            ExchangeRateResponse exchangeRateResponse = gson.fromJson(json, ExchangeRateResponse.class);

            if (response.statusCode() != 200){
                throw new IOException("Falha ao buscar a taxa de câmbio.  Status Code: " + response.statusCode());
            }

            return new ExchangeRate(exchangeRateResponse);
        } catch (IOException | InterruptedException e){
            throw new RuntimeException("Erro ao buscar a taxa de câmbio. " + e.getMessage() + e);

        }

    }

    public Set<String> getSupportedCurrencies() {

        String apiUrlCurrencies = apiUrl + "/codes";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrlCurrencies))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IOException("Erro ao buscar as moedas suportadas. Status code: " + response.statusCode());
            }

            String json = response.body();
            Gson gson = new GsonBuilder().create();

            SupportedCurrenciesResponse currenciesResponse = gson.fromJson(json, SupportedCurrenciesResponse.class);

            Set<String> supportedCurrencies = new HashSet<>();
            for (String[] code : currenciesResponse.supported_codes) {
                supportedCurrencies.add(code[0]);
            }

            return supportedCurrencies;
        } catch (IOException | InterruptedException e)  {
            throw new RuntimeException("Falha ao buscar moedas suportadas" + e.getMessage() + e);
        }
    }
}