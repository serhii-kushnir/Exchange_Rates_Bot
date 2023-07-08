package com.example.Info_ExchangeBot.service.utilits.commands;

import com.example.Info_ExchangeBot.banks.nbu.CurrencyServiceNBU;
import com.example.Info_ExchangeBot.banks.privatbank.CurrencyServicePrivatBank;
import com.example.Info_ExchangeBot.service.TelegramBot;
import com.example.Info_ExchangeBot.service.utilits.MessageBuilder;

import java.time.LocalDate;

public class BotCommandsHandler {

    private final String[] TIME = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "Виключити сповіщення"};

    private final MessageBuilder messageBuilder;

    public BotCommandsHandler(TelegramBot telegramBot) {
        this.messageBuilder = new MessageBuilder(telegramBot);
    }

    public void start(long chatId) {
        messageBuilder.createMessage(chatId,
                "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!",
                new String[]{"Отримати інфо", "Налаштування"});
    }

    public void infoMessage(long chatId, String currency, String callbackQuery) {
        LocalDate currentDate = LocalDate.now();
        StringBuilder answer = new StringBuilder("Курс валют на поточну дату: " + currentDate + "\n\n");

        switch (callbackQuery) {
//            case "НБУ" -> answer.append(CurrencyServiceNBU.getCurrencyInformation(currency));
//            case "ПРИВАТ" -> answer.append(CurrencyServicePrivatBank.getCurrencyInformation(currency, callbackQuery));
            case "ОТРИМАТИ ІНФО", "/info" -> {
                answer.append(CurrencyServicePrivatBank.getCurrencyInformation(currency, callbackQuery));
                answer.append(CurrencyServiceNBU.getCurrencyInformation(currency));
            }
        }
        messageBuilder.createMessage(chatId, answer.toString());
    }

    public void infoMessage(long chatId, String currency, String currencyTwo, String callbackQuery) {
        LocalDate currentDate = LocalDate.now();
        StringBuilder answer = new StringBuilder("Курс валют на поточну дату: " + currentDate + "\n\n");

        switch (callbackQuery) {
            case "НБУ" -> bankHandler(chatId, currency, callbackQuery);
            case "ПРИВАТ" -> answer.append(CurrencyServicePrivatBank.getCurrencyInformation(currency, currencyTwo, callbackQuery));
            case "ОТРИМАТИ ІНФО", "/info" -> {
                answer.append(CurrencyServicePrivatBank.getCurrencyInformation(currency, currencyTwo, callbackQuery));
                answer.append(CurrencyServiceNBU.getCurrencyInformation(currency, currencyTwo));
            }
        }
        messageBuilder.createMessage(chatId, answer.toString());
    }

    public void settingsMessage(long chatId) {
        messageBuilder.createMessage(chatId,
                "Налаштування",
                new String[]{"Банк", "Валюта", "Час сповіщень", "Кількість знаків після коми"});
    }

    public void numberSettings(long chatId) {
        messageBuilder.createMessage(chatId,
                "Виберіть кількість знаків після коми",
                new String[]{"2", "3", "4"});
    }

    public void CurrencyFormat(long chatId, String callbackQuery, String currency) {
        CurrencyServicePrivatBank.bayFormat(currency, callbackQuery);
        CurrencyServicePrivatBank.setchatId(chatId);

        messageBuilder.createMessage(chatId, "Кількість знаків післякими буде: " + callbackQuery);
        System.out.println(CurrencyServicePrivatBank.bayFormat(currency, callbackQuery));
        System.out.println("CurrencyFormat " + callbackQuery);
        System.out.println("chat " + CurrencyServicePrivatBank.getchatId());
    }

    public void currencySettings(long chatId) {
        messageBuilder.createMessage(chatId,
                "Виберіть валюту",
                new String[]{"EUR", "USD"});
    }

    public void bankSettings(long chatId) {
        messageBuilder.createMessage(chatId,
                "Виберіть банк",
                new String[]{"НБУ", "Приват", "Всі банки"});
    }

    public void bankHandler(long chatId, String currency, String callbackQuery) {
        StringBuilder answer = new StringBuilder("Ви обрали ");

        switch (callbackQuery) {
            case "НБУ" -> {
                answer.append("НБУ банк");
                bankInfo(chatId, currency, callbackQuery);
            }
            case "ПРИВАТ" -> answer.append("Приват банк");
            case "ВСІ БАНКИ" -> answer.append("всі банки");
        }

        messageBuilder.createMessage(chatId, answer.toString());
    }
    public void  bankInfo(long chatId, String callbackQuery, String currency) {
        LocalDate currentDate = LocalDate.now();
        StringBuilder answer = new StringBuilder("Курс валют на поточну дату: " + currentDate + "\n\n");

        switch (callbackQuery) {
            case "НБУ" -> answer.append(CurrencyServiceNBU.getCurrencyInformation(currency));
            case "ПРИВАТ" -> answer.append(CurrencyServicePrivatBank.getCurrencyInformation(currency, callbackQuery));
            case "ОТРИМАТИ ІНФО", "/info" -> {
                answer.append(CurrencyServicePrivatBank.getCurrencyInformation(currency, callbackQuery));
                answer.append(CurrencyServiceNBU.getCurrencyInformation(currency));
            }
        }

//        messageBuilder.createMessage(chatId, answer.toString());
    }

    public void timeSettings(long chatId) {
        messageBuilder.createMessage(chatId,
                "Виберіть час сповіщення",
                TIME);
    }
}