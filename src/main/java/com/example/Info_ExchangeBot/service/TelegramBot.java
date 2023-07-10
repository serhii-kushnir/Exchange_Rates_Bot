package com.example.Info_ExchangeBot.service;

import com.example.Info_ExchangeBot.config.BotConfig;
import com.example.Info_ExchangeBot.service.utilits.Log;
import com.example.Info_ExchangeBot.service.utilits.ScheduledMessageSender;
import com.example.Info_ExchangeBot.service.utilits.commands.BotCommandListMenu;
import com.example.Info_ExchangeBot.service.utilits.commands.BotCommandsHandler;
import com.example.Info_ExchangeBot.service.utilits.commands.ProcessHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

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
        ProcessHandler processHandler = new ProcessHandler(this);

        long chatId = 0;
        String userName = null;
        String receivedMessage;

        if (update.hasMessage()) {//&& update.getMessage().hasText()
            chatId = update.getMessage().getChatId();
            userName = update.getMessage().getFrom().getFirstName();
            receivedMessage = update.getMessage().getText();


            processHandler.message(receivedMessage, userName, chatId);

            if (update.getMessage().hasText()) {

            }
        }

        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();;
            userName = update.getCallbackQuery().getFrom().getFirstName();
            receivedMessage = update.getCallbackQuery().getData();

            processHandler.callbackQuery(receivedMessage, userName, chatId);
        }
    }

    public void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }
}