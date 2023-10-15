package ua.info_exchange_bot.service;

import ua.info_exchange_bot.config.BotConfig;
import ua.info_exchange_bot.service.utilits.Log;
import ua.info_exchange_bot.service.utilits.commands.BotCommandListMenu;

import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public final class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;
    private final ProcessHandler processHandler;

    public TelegramBot(final BotConfig config) {
        this.config = config;
        this.processHandler = new ProcessHandler(this);
        List<BotCommand> botCommandList = BotCommandListMenu.getBotCommandList();

        try {
            this.execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            Log.error(e);
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
    public void onUpdateReceived(final Update update) {
        long chatId;
        long messageId;
        String userName;
        String receivedMessage;

        if (update.hasMessage() && update.getMessage().hasText()) {
            chatId = update.getMessage().getChatId();
            userName = update.getMessage().getFrom().getFirstName();
            receivedMessage = update.getMessage().getText();

            processHandler.message(receivedMessage, userName, chatId);
        }

        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            receivedMessage = update.getCallbackQuery().getData();
            messageId = update.getCallbackQuery().getMessage().getMessageId();

            processHandler.callbackQuery(receivedMessage, userName, chatId, messageId);
        }
    }

    public void executeMessage(final SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            Log.error(e);
        }
    }
}
