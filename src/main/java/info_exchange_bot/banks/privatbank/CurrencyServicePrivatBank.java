package info_exchange_bot.banks.privatbank;

import java.io.IOException;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class CurrencyServicePrivatBank {
    private static final Logger LOGGER = Logger.getLogger(CurrencyServicePrivatBank.class.getName());
    private static final String BASE_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=11";
    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();

    private CurrencyServicePrivatBank() {
    }

    public static List<CurrencyModelPrivatBank> getCurrencyRate() {
        HttpGet request = new HttpGet(BASE_URL);

        try {
            HttpResponse response = HTTP_CLIENT.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity());

                return GSON.fromJson(responseBody, new TypeToken<List<CurrencyModelPrivatBank>>() { }.getType());
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch currency rate from MonoBank API", e);
        }

        return null;
    }

    public static String getCurrencyInformation(final String currency) {
        return getInfo(currency);
    }

    public static String getCurrencyInformation(final String currency, final String currencyTwo) {
        return getInfo(currency) + getInfo(currencyTwo);
    }

    private static String getInfo(final String currency) {
        List<CurrencyModelPrivatBank> currencyList = getCurrencyRate();
        StringBuilder result = new StringBuilder();

        if (currencyList != null) {
            for (CurrencyModelPrivatBank currencyModelPrivatbank : currencyList) {
                if (currencyModelPrivatbank.getCcy().equals(currency)) {
                    result.append("Курс в Приватбанк: ")
                            .append(currencyModelPrivatbank.getCcy())
                            .append("/")
                            .append(currencyModelPrivatbank.getBaseCcy())
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
}
