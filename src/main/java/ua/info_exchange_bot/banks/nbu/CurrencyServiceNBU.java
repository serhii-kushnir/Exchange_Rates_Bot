package ua.info_exchange_bot.banks.nbu;

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

public final class CurrencyServiceNBU {
    private static final Logger LOGGER = Logger.getLogger(CurrencyServiceNBU.class.getName());
    private static final String BASE_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();

    private CurrencyServiceNBU() {
    }

    public static List<CurrencyModelNBU> getCurrencyRate() {
        HttpGet request = new HttpGet(BASE_URL);

        try {
            HttpResponse response = HTTP_CLIENT.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity());

                return  GSON.fromJson(responseBody, new TypeToken<List<CurrencyModelNBU>>() { }.getType());
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch currency rate from MonoBank API", e);
        }

        return null;
    }

    public static String getCurrencyInformation(final String currency) {
        List<CurrencyModelNBU> currencyList = getCurrencyRate();
        StringBuilder result = new StringBuilder();

        if (currencyList != null) {
            for (CurrencyModelNBU currencyModelNBU : currencyList) {
                if (currencyModelNBU.getCc().equals(currency)) {
                    result.append("Курс в НБУ: ")
                            .append(currencyModelNBU.getCc())
                            .append("/UAH")
                            .append("\nКурс: ")
                            .append(currencyModelNBU.getRate())
                            .append("\n\n");
                }
            }
        }

        return result.toString();
    }
}
