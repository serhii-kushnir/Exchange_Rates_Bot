package ua.info_exchange_bot.service.utilits;

import ua.info_exchange_bot.service.TelegramBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public final class MessageBuilder {

    private final SendMessage sendMessage;
    private final TelegramBot telegramBot;

    public MessageBuilder(final TelegramBot telegramBot) {
        this.sendMessage = new SendMessage();
        this.telegramBot = telegramBot;
    }

    public void createMessage(final long chatId, final String answer, final InlineKeyboardMarkup button) {
        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);

        sendMessage.setReplyMarkup(button);

        telegramBot.executeMessage(sendMessage);
    }

    public void editMessage(final String text, final long chatId, final long messageId, final InlineKeyboardMarkup button) {
        EditMessageText message = new EditMessageText();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.setMessageId((int) messageId);

        message.setReplyMarkup(button);

        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            Log.error(e);
        }
    }
}
