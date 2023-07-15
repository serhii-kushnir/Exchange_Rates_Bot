package com.example.Info_ExchangeBot.service.utilits.ui;

import com.example.Info_ExchangeBot.banks.monobank.CurrencyServiceMonoBank;
import com.example.Info_ExchangeBot.banks.nbu.CurrencyServiceNBU;
import com.example.Info_ExchangeBot.banks.privatbank.CurrencyServicePrivatBank;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServices {

    private static final Map<Long, UserModel> USERS_SETTINGS = new HashMap<>();

    public static UserModel getUserSettingsById(long chatId) {
        return USERS_SETTINGS.get(chatId);
    }

    public static boolean isUserSettingsExists(long chatId) {
        return USERS_SETTINGS.containsKey(chatId);
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

    public static String getCurrencyInformationFromSelectedBank(long chatId) {
        var currentDate = LocalDate.now();
        StringBuilder answer = new StringBuilder("Курс на поточну дату: " + currentDate);
        answer.append("\n\n");

        if (checkSelectedBank(chatId).equals("Приват")) {
            answer.append(CurrencyServicePrivatBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency()));
        } else if (checkSelectedBank(chatId).equals("НБУ")) {
            answer.append(CurrencyServiceNBU.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency()));
        } else if (checkSelectedBank(chatId).equals("Mоно")) {
            answer.append(CurrencyServiceMonoBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency()));
        } else if (checkSelectedBank(chatId).equals("Всі банки")) {
            answer.append(CurrencyServicePrivatBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency()));
            answer.append(CurrencyServiceMonoBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency()));
            answer.append(CurrencyServiceNBU.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency()));
        }

        return answer.toString();
    }

    public static String toNumberFormat(long chatId){
        String response = getCurrencyInformationFromSelectedBank(chatId);
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
        String result = "#0.00";
        String countNumbers = getUserSettingsById(chatId).getNumber();

        switch (countNumbers){
            case "2" -> result = "#0.00";
            case "3" -> result = "#0.000";
            case "4" -> result = "#0.0000";
        }

        return result;
    }
}
