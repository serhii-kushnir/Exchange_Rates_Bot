package com.example.Info_ExchangeBot.banks.privatbank;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public class CurrencyServicePrivatBank {

    private static long chat;
    private static int nem = 2;
    private static final String BASE_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=11";
    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();
    private static BigDecimal decimal;


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

    public static String getCurrencyInformation(String currency, String callbackQuery) {

        System.out.println("getCurrencyInformation " + currency + " " + currency);
        return getInfo(currency, callbackQuery);
    }

    public static String getCurrencyInformation(String currency, String currencyTwo, String callbackQuery) {
        return getInfo(currency,  callbackQuery) + getInfo(currencyTwo, callbackQuery);
    }

    private static String getInfo(String currency, String callbackQuery) {
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
                            .append(bayFormat(currency, callbackQuery))
                            .append("\nПродаж: ")
                            .append(currencyModelPrivatbank.getSale())
                            .append("\n\n");
                }
            }
        }
        System.out.println("getInfo " + currency + " " + callbackQuery);
        return result.toString();
    }

    public static String getBuy(String currency) {
        List<CurrencyModelPrivatBank> currencyList = getCurrencyRate();
        StringBuilder result = new StringBuilder();

        if (currencyList != null) {
            for (CurrencyModelPrivatBank currencyModelPrivatbank : currencyList) {
                if (currencyModelPrivatbank.getCcy().equals(currency)) {
                    result.append(currencyModelPrivatbank.getBuy());
                }
            }
        }

        return result.toString();
    }

    public static String getSale(String currency) {
        List<CurrencyModelPrivatBank> currencyList = getCurrencyRate();
        StringBuilder result = new StringBuilder();

        if (currencyList != null) {
            for (CurrencyModelPrivatBank currencyModelPrivatbank : currencyList) {
                if (currencyModelPrivatbank.getCcy().equals(currency)) {
                    result.append(currencyModelPrivatbank.getSale());
                }
            }
        }

        return result.toString();
    }

    public static String bayFormat(String currency, String callbackQuery) {
        double bay = Double.parseDouble(getBuy(currency));

        decimalFormat(bay, callbackQuery);

        System.out.println("bayFormat " + bay + " " +  callbackQuery);
        return String.valueOf(decimal);
    }


    private static void decimalFormat(double price, String callbackQuery) {

        if (chat ==  getchatId()) {
            switch (callbackQuery) {
                case "2", "3", "4" -> nem = Integer.parseInt(callbackQuery);
            }
        }


        decimal = new BigDecimal(price).setScale(nem, RoundingMode.HALF_UP);

        System.out.println("decimalFormat " + callbackQuery);
    }

    public static void setchatId(long chatId) {
        chat = chatId;
    }

    public static long getchatId() {
        return chat;
    }

}