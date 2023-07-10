package com.example.Info_ExchangeBot.service.utilits.commands;

import com.example.Info_ExchangeBot.service.TelegramBot;
import com.example.Info_ExchangeBot.service.utilits.Log;
import com.example.Info_ExchangeBot.service.utilits.ScheduledMessageSender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ProcessHandler {
    private boolean messageSent;
    private final ScheduledMessageSender scheduledMessageSender;
    private final String USD = "USD";
    private final String EUR = "EUR";
    private final BotCommandsHandler botCommandsHandler;

    public ProcessHandler(TelegramBot telegramBot) {
        this.botCommandsHandler = new BotCommandsHandler(telegramBot);
        this.scheduledMessageSender = new ScheduledMessageSender(telegramBot);
        this.messageSent = false;
    }

    LocalDateTime scheduledTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 14));
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

        scheduledMessageSender.scheduleMessage(chatId, scheduledTime);

        Log.Info(username, messageText);
    }

    public void callbackQuery(String chatId, String username, long chatIdBackQuery) {
        switch (chatId) {
            case "ОТРИМАТИ ІНФО" -> botCommandsHandler.infoMessage(chatIdBackQuery, USD, EUR, chatId);
            case "НАЛАШТУВАННЯ" -> botCommandsHandler.settingsMessage(chatIdBackQuery);
            case "КІЛЬКІСТЬ ЗНАКІВ ПІСЛЯ КОМИ" -> botCommandsHandler.numberSettings(chatIdBackQuery);
            case "ВАЛЮТА" -> botCommandsHandler.currencySettings(chatIdBackQuery);
            case "БАНК" -> botCommandsHandler.bankSettings(chatIdBackQuery);
            case "ЧАС СПОВІЩЕНЬ" -> botCommandsHandler.timeSettings(chatIdBackQuery);
            case "ПРИВАТ", "НБУ", "ВСІ БАНКИ" -> botCommandsHandler.bankHandler(chatIdBackQuery, USD, chatId);
            case "2", "3", "4" -> botCommandsHandler.CurrencyFormat(chatIdBackQuery, chatId, USD);
        }

        Log.button(username, chatId);
    }

}