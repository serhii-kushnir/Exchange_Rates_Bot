package ua.info_exchange_bot.service.ui;

import ua.info_exchange_bot.banks.monobank.CurrencyServiceMonoBank;
import ua.info_exchange_bot.banks.nbu.CurrencyServiceNBU;
import ua.info_exchange_bot.banks.privatbank.CurrencyServicePrivatBank;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.info_exchange_bot.service.utilits.Constants.ALL_CURRENCIES;
import static ua.info_exchange_bot.service.utilits.Constants.ALL_BANKS;
import static ua.info_exchange_bot.service.utilits.Constants.BANK_PRIVAT;
import static ua.info_exchange_bot.service.utilits.Constants.BANK_NBU;
import static ua.info_exchange_bot.service.utilits.Constants.BANK_MONO;
import static ua.info_exchange_bot.service.utilits.Constants.EUR;
import static ua.info_exchange_bot.service.utilits.Constants.USD;

public class UserServices {

    private static final Map<Long, UserModel> USERS_SETTINGS = new HashMap<>();

    public static UserModel getUserSettingsById(long chatId) {
        return USERS_SETTINGS.get(chatId);
    }

    public static boolean isUserSettingsExists(long chatId) {
        return USERS_SETTINGS.containsKey(chatId);
    }

    private UserServices() {
    }

    public static void createUserSettings(long chatId) {
        if (!isUserSettingsExists(chatId)) {
            UserModel userModel = new UserModel();
            USERS_SETTINGS.put(chatId, userModel);
        }
    }

    public static String checkSelectedBank(long chatId) {
         return USERS_SETTINGS.get(chatId).getBank();
    }

    public static String checkSelectedCurrency(long chatId) {
        return USERS_SETTINGS.get(chatId).getCurrency();
    }

    public static String getInformation(long chatId) {
        LocalDate currentDate = LocalDate.now();
        StringBuilder answer = new StringBuilder("Курс на поточну дату: " + currentDate);
        answer.append("\n__________________________________\n\n");

        if (checkSelectedBank(chatId).equals(ALL_BANKS) && checkSelectedCurrency(chatId).equals(USD)
                || checkSelectedBank(chatId).equals(ALL_BANKS) && checkSelectedCurrency(chatId).equals(EUR)) {
            answer.append(CurrencyServicePrivatBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency()));
            answer.append(CurrencyServiceMonoBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency()));
            answer.append(CurrencyServiceNBU.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency()));
        } else if (checkSelectedBank(chatId).equals(ALL_BANKS) && checkSelectedCurrency(chatId).equals(ALL_CURRENCIES)) {

            answer.append(CurrencyServicePrivatBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getUsd()));
            answer.append(CurrencyServicePrivatBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getEur()));

            answer.append("__________________________________\n\n");
            answer.append(CurrencyServiceMonoBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getUsd()));
            answer.append(CurrencyServiceMonoBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getEur()));
            answer.append("__________________________________\n\n");

            answer.append(CurrencyServiceNBU.getCurrencyInformation(USERS_SETTINGS.get(chatId).getUsd()));
            answer.append(CurrencyServiceNBU.getCurrencyInformation(USERS_SETTINGS.get(chatId).getEur()));
        }

        if (checkSelectedBank(chatId).equals(BANK_PRIVAT) && checkSelectedCurrency(chatId).equals(USD)
                || checkSelectedBank(chatId).equals(BANK_PRIVAT) && checkSelectedCurrency(chatId).equals(EUR)) {
            answer.append(CurrencyServicePrivatBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency()));
        } else if (checkSelectedBank(chatId).equals(BANK_PRIVAT) && checkSelectedCurrency(chatId).equals(ALL_CURRENCIES)) {
            answer.append(CurrencyServicePrivatBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getUsd()));
            answer.append(CurrencyServicePrivatBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getEur()));
        }

        if (checkSelectedBank(chatId).equals(BANK_NBU) && checkSelectedCurrency(chatId).equals(USD)
                || checkSelectedBank(chatId).equals(BANK_NBU) && checkSelectedCurrency(chatId).equals(EUR)) {
            answer.append(CurrencyServiceNBU.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency()));
        } else if (checkSelectedBank(chatId).equals(BANK_NBU) && checkSelectedCurrency(chatId).equals(ALL_CURRENCIES)) {
            answer.append(CurrencyServiceNBU.getCurrencyInformation(USERS_SETTINGS.get(chatId).getUsd()));
            answer.append(CurrencyServiceNBU.getCurrencyInformation(USERS_SETTINGS.get(chatId).getEur()));
        }

        if (checkSelectedBank(chatId).equals(BANK_MONO) && checkSelectedCurrency(chatId).equals(USD)
                || checkSelectedBank(chatId).equals(BANK_MONO) && checkSelectedCurrency(chatId).equals(EUR)) {
            answer.append(CurrencyServicePrivatBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency()));
        } else if (checkSelectedBank(chatId).equals(BANK_MONO) && checkSelectedCurrency(chatId).equals(ALL_CURRENCIES)) {
            answer.append(CurrencyServiceMonoBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getUsd()));
            answer.append(CurrencyServiceMonoBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getEur()));
        }


        return answer.toString();
    }

    public static String toNumberFormat(long chatId){
        String response = getInformation(chatId);
        String format = getFormat(chatId);

        DecimalFormat decimalFormat = new DecimalFormat(format);
        Pattern pattern = Pattern.compile("\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(response);
        StringBuilder output = new StringBuilder();

        while (matcher.find()) {
            String matchedNumber = matcher.group();
            String formattedNumber = decimalFormat.format(Double.parseDouble(matchedNumber));
            matcher.appendReplacement(output, formattedNumber);
        }
        matcher.appendTail(output);

        return output.toString();
    }

    private static String getFormat(long chatId){
        String result;
        String countNumbers = getUserSettingsById(chatId).getNumber();

        switch (countNumbers){
            case "3" -> result = "#0.000";
            case "4" -> result = "#0.0000";
            default -> result = "#0.00";
        }

        return result;
    }
}
