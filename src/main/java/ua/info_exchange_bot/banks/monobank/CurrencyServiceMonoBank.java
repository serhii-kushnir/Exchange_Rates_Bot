package ua.info_exchange_bot.banks.monobank;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ua.info_exchange_bot.service.utilits.Constants.ONE_MINUTE;
import static ua.info_exchange_bot.service.utilits.Constants.CURRENCY_NUMBER;
import static ua.info_exchange_bot.service.utilits.Constants.CURRENCY_NUMBER_TO_USD;
import static ua.info_exchange_bot.service.utilits.Constants.CURRENCY_NUMBER_TO_EUR;


public final class CurrencyServiceMonoBank {
    private static final Logger LOGGER = Logger.getLogger(CurrencyServiceMonoBank.class.getName());
    private static final String BASE_URL = "https://api.monobank.ua/bank/currency";
    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();
    private static List<CurrencyModelMonoBank> lastCurrencyList;
    private static long lastUpdateTime;

    private CurrencyServiceMonoBank() {
    }

    public static List<CurrencyModelMonoBank> getCurrencyRate() {
        long currentTime = System.currentTimeMillis();

        if (lastCurrencyList != null && (currentTime - lastUpdateTime) < ONE_MINUTE) {
            return lastCurrencyList;
        }

        HttpGet request = new HttpGet(BASE_URL);

        try {
            HttpResponse response = HTTP_CLIENT.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity());
                List<CurrencyModelMonoBank> currencyList = GSON.fromJson(responseBody, new TypeToken<List<CurrencyModelMonoBank>>() { }.getType());

                if (currencyList != null) {
                    lastCurrencyList = currencyList;
                    lastUpdateTime = currentTime;

                    return currencyList;
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch currency rate from MonoBank API", e);
        }

        return lastCurrencyList;
    }

    public static String getCurrencyInformation(final String currency) {
        List<CurrencyModelMonoBank> currencyList = getCurrencyRate();
        StringBuilder result = new StringBuilder();

        if (currencyList != null) {
            for (CurrencyModelMonoBank currencyModelMonoBank : currencyList) {
                if (currencyModelMonoBank.getCurrencyCodeA() == convertCurrencyToNumber(currency)
                        && currencyModelMonoBank.getCurrencyCodeB() == CURRENCY_NUMBER) {
                    result.append("Курс в Монобанк: ")
                            .append(currency)
                            .append("/UAH\nКупівля: ")
                            .append(currencyModelMonoBank.getRateBuy())
                            .append("\nПродаж: ")
                            .append(currencyModelMonoBank.getRateSell())
                            .append("\n\n");
                }
            }
        }

        return result.toString();
    }

    private static int convertCurrencyToNumber(final String currency) {
        int currencyNumber = 0;

        if (currency.contains("USD")) {
            currencyNumber = CURRENCY_NUMBER_TO_USD;
        } else if (currency.contains("EUR")) {
            currencyNumber = CURRENCY_NUMBER_TO_EUR;
        }

        return currencyNumber;
    }
}
