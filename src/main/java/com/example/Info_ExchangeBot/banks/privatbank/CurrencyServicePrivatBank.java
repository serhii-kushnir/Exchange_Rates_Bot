package com.example.Info_ExchangeBot.banks.privatbank;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CurrencyServicePrivatBank {
    private static final String BASE_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=11";
    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();

    public static List<CurrencyModelPrivatBank> getCurrencyRate() {
        HttpGet request = new HttpGet(BASE_URL);

        try {
            HttpResponse response = HTTP_CLIENT.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity());
                CurrencyModelPrivatBank[] tasks = GSON.fromJson(responseBody, CurrencyModelPrivatBank[].class);
                return Arrays.asList(tasks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getCurrencyInformation(String currency) {
        List<CurrencyModelPrivatBank> currencyList = getCurrencyRate();
        StringBuilder result = new StringBuilder();

        if (currencyList != null) {
            for (CurrencyModelPrivatBank currencyModelPrivatbank : currencyList) {
                if (currencyModelPrivatbank.getCcy().equals(currency)) {
                    result.append("Курс в Приватбанк: ")
                            .append(currencyModelPrivatbank.getCcy())
                            .append("/")
                            .append(currencyModelPrivatbank.getBase_ccy())
                            .append("\nКупівля: ")
                            .append(currencyModelPrivatbank.getBuy())
                            .append("\nПродаж: ")
                            .append(currencyModelPrivatbank.getSale())
                            .append("\n\n");
                }
            }
        }

        return result.toString();
    }

    public static String getCurrencyInformation(String currency, String currency2) {
        List<CurrencyModelPrivatBank> currencyList = getCurrencyRate();
        StringBuilder result = new StringBuilder();

        if (currencyList != null) {
            for (CurrencyModelPrivatBank currencyModelPrivatbank : currencyList) {
                if (currencyModelPrivatbank.getCcy().equals(currency)) {
                    result.append("Курс в Приватбанк: ")
                            .append(currencyModelPrivatbank.getCcy())
                            .append("/")
                            .append(currencyModelPrivatbank.getBase_ccy())
                            .append("\nКупівля: ")
                            .append(currencyModelPrivatbank.getBuy())
                            .append("\nПродаж: ")
                            .append(currencyModelPrivatbank.getSale())
                            .append("\n\n");

                }
            }
        }

        if (currencyList != null) {
            for (CurrencyModelPrivatBank currencyModelPrivatbank : currencyList) {
                if (currencyModelPrivatbank.getCcy().equals(currency2)) {
                    result.append("Курс в Приватбанк: ")
                            .append(currencyModelPrivatbank.getCcy())
                            .append("/")
                            .append(currencyModelPrivatbank.getBase_ccy())
                            .append("\nКупівля: ")
                            .append(currencyModelPrivatbank.getBuy())
                            .append("\nПродаж: ")
                            .append(currencyModelPrivatbank.getSale())
                            .append("\n\n");

                }
            }
        }

        return result.toString();
    }

    private static String ggg(String currency) {
        List<CurrencyModelPrivatBank> currencyList = getCurrencyRate();
        StringBuilder result = new StringBuilder();

        if (currencyList != null) {
            for (CurrencyModelPrivatBank currencyModelPrivatbank : currencyList) {
                if (currencyModelPrivatbank.getCcy().equals(currency)) {
                    result.append("Курс в Приватбанк: ")
                            .append(currencyModelPrivatbank.getCcy())
                            .append("/")
                            .append(currencyModelPrivatbank.getBase_ccy())
                            .append("\nКупівля: ")
                            .append(currencyModelPrivatbank.getBuy())
                            .append("\nПродаж: ")
                            .append(currencyModelPrivatbank.getSale())
                            .append("\n\n");
                }
            }
        }

        return result.toString();
    }

//    public static String getCurrency(String currency) {
//        List<CurrencyModelPrivatBank> currencyList = getCurrencyRate();
//        StringBuilder result = new StringBuilder();
//
//        if (currencyList != null) {
//            for (CurrencyModelPrivatBank currencyModelPrivatbank : currencyList) {
//                if (currencyModelPrivatbank.getCcy().equals(currency)) {
//                    result.append(currencyModelPrivatbank.getCcy());
//                }
//            }
//        }
//
//        return result.toString();
//    }
}