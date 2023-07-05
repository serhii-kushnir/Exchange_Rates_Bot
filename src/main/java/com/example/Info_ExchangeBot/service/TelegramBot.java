package com.example.Info_ExchangeBot.service;

import com.example.Info_ExchangeBot.config.BotConfig;
import com.example.Info_ExchangeBot.service.utilits.Log;
import com.example.Info_ExchangeBot.service.utilits.commands.BotCommandListMenu;
import com.example.Info_ExchangeBot.service.utilits.commands.BotCommands;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;
    private BotCommands botCommands;

    public TelegramBot(BotConfig config) {
        this.config = config;

        List<BotCommand> botCommandList = BotCommandListMenu.getBotCommandList();

        try {
            this.execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String username = update.getMessage().getChat().getUserName();
            long chatId = update.getMessage().getChatId();

            processMessage(messageText, username, chatId);
        }

        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatIdBackQuery = update.getCallbackQuery().getMessage().getChatId();

            processCallbackQuery(callbackData, chatIdBackQuery);
        }
    }

    private void processMessage(String messageText, String username, long chatId) {
        botCommands = new BotCommands(new TelegramBot(config));

        switch (messageText) {
            case "/start" -> botCommands.start(chatId);
            case "/info" -> {
                botCommands.infoMessage(chatId, "USD");
                botCommands.infoMessage(chatId, "EUR");
            }
            case "/setting" -> botCommands.settingsMessage(chatId);
            case "/bank" -> botCommands.bankSettings(chatId);
            case "/currency" -> botCommands.currencySettings(chatId);
            case "/time" -> botCommands.timeSettings(chatId);
            case "/number" -> botCommands.numberSettings(chatId);
        }

        Log.Info(username, messageText);
    }

    private void processCallbackQuery(String callbackData, long chatIdBackQuery) {
        botCommands = new BotCommands(new TelegramBot(config));

        switch (callbackData) {
            case "ОТРИМАТИ ІНФО" -> {
                botCommands.infoMessage(chatIdBackQuery, "USD");
                botCommands.infoMessage(chatIdBackQuery, "EUR");
            }
            case "НАЛАШТУВАННЯ" -> botCommands.settingsMessage(chatIdBackQuery);
            case "КІЛЬКІСТЬ ЗНАКІВ ПІСЛЯ КОМИ" -> botCommands.numberSettings(chatIdBackQuery);
            case "ВАЛЮТА" -> botCommands.currencySettings(chatIdBackQuery);
            case "БАНК" -> botCommands.bankSettings(chatIdBackQuery);
            case "ЧАС СПОВІЩЕНЬ" -> botCommands.timeSettings(chatIdBackQuery);
        }

        Log.button(callbackData);
    }

    public void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }
}