package com.example.Info_ExchangeBot.service.utilits.commands;

import com.example.Info_ExchangeBot.banks.nbu.CurrencyServiceNBU;
import com.example.Info_ExchangeBot.banks.privatbank.CurrencyServicePrivatBank;
import com.example.Info_ExchangeBot.service.TelegramBot;
import com.example.Info_ExchangeBot.service.utilits.MessageBuilder;

public class BotCommands {

    private String bank;
    private final String[] TIME = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "Виключити сповіщення"};

    private final MessageBuilder messageBuilder;

    public BotCommands(TelegramBot telegramBot) {
        this.messageBuilder = new MessageBuilder(telegramBot);
    }

    public void start(long chatId) {
        messageBuilder.createMessage(chatId,
                "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!",
                new String[]{"Отримати інфо", "Налаштування"});
    }

    public void infoMessage(long chatId, String currency, String bank) {
        StringBuilder answer = new StringBuilder();

        switch (bank) {
            case "НБУ" -> answer.append(CurrencyServiceNBU.getCurrencyInformation(currency));
            case "ПРИВАТ" -> answer.append(CurrencyServicePrivatBank.getCurrencyInformation(currency));
            case "ОТРИМАТИ ІНФО" -> {
                answer.append(CurrencyServicePrivatBank.getCurrencyInformation(currency));
                answer.append(CurrencyServiceNBU.getCurrencyInformation(currency));
            }
            case "/info" -> {
                answer.append(CurrencyServicePrivatBank.getCurrencyInformation(currency));
                answer.append(CurrencyServiceNBU.getCurrencyInformation(currency));
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

    public void currencySettings(long chatId) {
        messageBuilder.createMessage(chatId,
                "Виберіть валюту",
                new String[]{"EUR", "USD"});
    }

    public void bankSettings(long chatId) {
        messageBuilder.createMessage(chatId,
                "Виберіть банк",
                new String[]{"НБУ", "Приват"});
    }

    public void privatOnClick(long chatId, String bank) {
        this.bank += bank;
        System.out.println(this.bank);
    }

    public void nbuOnClick(long chatId, String bank) {
        this.bank += bank;
        System.out.println(this.bank);
    }

    public void timeSettings(long chatId) {
        messageBuilder.createMessage(chatId,
                "Виберіть час сповіщення",
                TIME);
    }
}