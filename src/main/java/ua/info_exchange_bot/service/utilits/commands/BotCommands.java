package ua.info_exchange_bot.service.utilits.commands;

import ua.info_exchange_bot.service.utilits.Buttons;
import ua.info_exchange_bot.service.utilits.Constants;
import ua.info_exchange_bot.service.utilits.MessageBuilder;
import ua.info_exchange_bot.service.TelegramBot;
import ua.info_exchange_bot.service.ui.UserServices;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static ua.info_exchange_bot.service.ui.UserServices.getUserSettingsById;

public final class BotCommands {

    private final SendMessage sendMessage;
    private final MessageBuilder messageBuilder;

    public BotCommands(final TelegramBot telegramBot) {
        this.sendMessage = new SendMessage();
        this.messageBuilder = new MessageBuilder(telegramBot);
    }

    public void start(final long chatId) {
        messageBuilder.createMessage(chatId, "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!", Buttons.start());
    }

    public void home(final long chatId) {
        messageBuilder.createMessage(chatId, "Ви повернулись на головне меню", Buttons.start());
    }

    public void infoMessage(final long chatId) {
        messageBuilder.createMessage(chatId, UserServices.toNumberFormat(chatId), Buttons.info());
    }

    public void settingsMessage(final long chatId) {
        messageBuilder.createMessage(chatId, "Налаштування", Buttons.setting());
    }

    public void numberSettings(final long chatId) {
        messageBuilder.createMessage(chatId, Constants.NUMBER, Buttons.number(chatId));
    }

    public void currencySettings(final long chatId) {
        messageBuilder.createMessage(chatId, Constants.CURRENCY, Buttons.currency(chatId));
    }

    public void bankSettings(final long chatId) {
        messageBuilder.createMessage(chatId, Constants.BANK, Buttons.bank(chatId));
    }

    public void timeSettings(final long chatId) {
        messageBuilder.createMessage(chatId, Constants.TIME, Buttons.time(chatId));
    }

    public void setTwoNumbers(final long chatId, final long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("2");

        messageBuilder.editMessage(Constants.NUMBER, chatId, messageId, Buttons.number(chatId));
    }

    public void setThreeNumbers(final long chatId, final long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("3");

        messageBuilder.editMessage(Constants.NUMBER, chatId, messageId, Buttons.number(chatId));
    }

    public void setFourNumbers(final long chatId, final long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("4");

        messageBuilder.editMessage(Constants.NUMBER, chatId, messageId, Buttons.number(chatId));
    }

    public void setUSD(final long chatId, final long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setCurrency("USD");

        messageBuilder.editMessage(Constants.CURRENCY, chatId, messageId, Buttons.currency(chatId));
    }

    public void setEUR(final long chatId, final long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setCurrency("EUR");

        messageBuilder.editMessage(Constants.CURRENCY, chatId, messageId, Buttons.currency(chatId));
    }

    public void setAllCurrency(final long chatId, final long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setCurrency("Всі валюти");

        messageBuilder.editMessage(Constants.CURRENCY, chatId, messageId, Buttons.currency(chatId));
    }

    public void setMono(final long chatId, final long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("Моно");

        messageBuilder.editMessage(Constants.BANK, chatId, messageId, Buttons.bank(chatId));
    }

    public void setPrivat(final long chatId, final long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("Приват");

        messageBuilder.editMessage(Constants.BANK, chatId, messageId, Buttons.bank(chatId));
    }

    public void setNBU(final long chatId, final long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("НБУ");

        messageBuilder.editMessage(Constants.BANK, chatId, messageId, Buttons.bank(chatId));
    }

    public void setAllBank(final long chatId, final long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("Всі банки");

        messageBuilder.editMessage(Constants.BANK, chatId, messageId, Buttons.bank(chatId));
    }
}
