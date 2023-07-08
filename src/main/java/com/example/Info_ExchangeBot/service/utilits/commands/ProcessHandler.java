package com.example.Info_ExchangeBot.service.utilits.commands;

import com.example.Info_ExchangeBot.service.TelegramBot;
import com.example.Info_ExchangeBot.service.utilits.Log;

public class ProcessHandler {

    private final String USD = "USD";
    private final String EUR = "EUR";
    private final BotCommandsHandler botCommandsHandler;

    public ProcessHandler(TelegramBot telegramBot) {
        this.botCommandsHandler = new BotCommandsHandler(telegramBot);
    }

    public void message(String messageText, String username, long chatId) {

        switch (messageText) {
            case "/start" -> botCommandsHandler.start(chatId);
            case "/info" -> botCommandsHandler.infoMessage(chatId, USD, EUR, messageText);
            case "/setting" -> botCommandsHandler.settingsMessage(chatId);
            case "/bank" -> botCommandsHandler.bankSettings(chatId);
            case "/currency" -> botCommandsHandler.currencySettings(chatId);
            case "/time" -> botCommandsHandler.timeSettings(chatId);
            case "/number" -> botCommandsHandler.numberSettings(chatId);
        }

        Log.Info(username, messageText);
    }

    public void callbackQuery(String callbackData, String username, long chatIdBackQuery) {
        switch (callbackData) {
            case "ОТРИМАТИ ІНФО" -> botCommandsHandler.infoMessage(chatIdBackQuery, USD, EUR, callbackData);
            case "НАЛАШТУВАННЯ" -> botCommandsHandler.settingsMessage(chatIdBackQuery);
            case "КІЛЬКІСТЬ ЗНАКІВ ПІСЛЯ КОМИ" -> botCommandsHandler.numberSettings(chatIdBackQuery);
            case "ВАЛЮТА" -> botCommandsHandler.currencySettings(chatIdBackQuery);
            case "БАНК" -> botCommandsHandler.bankSettings(chatIdBackQuery);
            case "ЧАС СПОВІЩЕНЬ" -> botCommandsHandler.timeSettings(chatIdBackQuery);
            case "ПРИВАТ", "НБУ", "ВСІ БАНКИ" -> botCommandsHandler.bankHandler(chatIdBackQuery, USD, callbackData);
            case "2", "3", "4" -> botCommandsHandler.CurrencyFormat(chatIdBackQuery, callbackData, USD);
        }

        Log.button(username, callbackData);
    }

}