package com.example.Info_ExchangeBot.service.message;

import com.example.Info_ExchangeBot.service.TelegramBot;
import com.example.Info_ExchangeBot.service.utilits.Log;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CreateMessage {

    private final SendMessage sendMessage;
    private final TelegramBot telegramBot;

    public CreateMessage(TelegramBot telegramBot) {
        this.sendMessage = new SendMessage();
        this.telegramBot = telegramBot;
    }

    public void createMessage(long chatId, String answer, InlineKeyboardMarkup button) {
        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);

        sendMessage.setReplyMarkup(button);

        telegramBot.executeMessage(sendMessage);
    }

    public void executeEditMessageText(String text, long chatId, long messageId, InlineKeyboardMarkup button){
        EditMessageText message = new EditMessageText();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.setMessageId((int) messageId);

        message.setReplyMarkup(button);

        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }
}

