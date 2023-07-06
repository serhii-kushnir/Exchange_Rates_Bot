package com.example.Info_ExchangeBot.service.utilits.commands;

import com.example.Info_ExchangeBot.banks.privatbank.CurrencyService;
import com.example.Info_ExchangeBot.service.TelegramBot;
import com.example.Info_ExchangeBot.service.utilits.MessageBuilder;

public class BotCommands {

    private final MessageBuilder messageBuilder;

    public BotCommands(TelegramBot telegramBot) {
        this.messageBuilder = new MessageBuilder(telegramBot);
    }

    public void start(long chatId) {
        messageBuilder.createMessage(chatId,
                "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!",
                new String[]{"Отримати інфо", "Налаштування"});
    }

    public void infoMessage(long chatId, String currency) {
        messageBuilder.createMessage(chatId,
                CurrencyService.getCurrencyInformation(currency));
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
                new String[]{"НБУ", "Моно", "Приват"});
    }

    public void timeSettings(long chatId) {
        messageBuilder.createMessage(chatId,
                "Виберіть час сповіщення",
                new String[]{"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "Виключити сповіщення"});
    }
}