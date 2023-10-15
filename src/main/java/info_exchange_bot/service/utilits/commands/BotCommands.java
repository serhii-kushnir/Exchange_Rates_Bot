package info_exchange_bot.service.utilits.commands;

import info_exchange_bot.service.utilits.Buttons;
import info_exchange_bot.service.utilits.Constants;
import info_exchange_bot.service.utilits.MessageBuilder;
import info_exchange_bot.service.TelegramBot;
import info_exchange_bot.service.ui.UserServices;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static info_exchange_bot.service.ui.UserServices.getUserSettingsById;

public class BotCommands {

    private final SendMessage sendMessage;
    private final MessageBuilder messageBuilder;

    public BotCommands(TelegramBot telegramBot) {
        this.sendMessage = new SendMessage();
        this.messageBuilder = new MessageBuilder(telegramBot);
    }

    public void start(long chatId) {
        UserServices.createUserSettings(chatId);

        messageBuilder.createMessage(chatId, "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!", Buttons.start());
    }

    public void home(long chatId) {
        UserServices.createUserSettings(chatId);

        messageBuilder.createMessage(chatId, "Ви повернулись на головне меню", Buttons.start());
    }

    public void infoMessage(long chatId) {
        UserServices.createUserSettings(chatId);

        messageBuilder.createMessage(chatId, UserServices.toNumberFormat(chatId), Buttons.info());
    }

    public void settingsMessage(long chatId) {
        UserServices.createUserSettings(chatId);

        messageBuilder.createMessage(chatId, "Налаштування", Buttons.setting());
    }

    public void numberSettings(long chatId) {
        UserServices.createUserSettings(chatId);

        messageBuilder.createMessage(chatId, Constants.NUMBER, Buttons.number(chatId));
    }

    public void currencySettings(long chatId) {
        UserServices.createUserSettings(chatId);

        messageBuilder.createMessage(chatId, Constants.CURRENCY, Buttons.currency(chatId));
    }

    public void bankSettings(long chatId) {
        UserServices.createUserSettings(chatId);

        messageBuilder.createMessage(chatId, Constants.BANK, Buttons.bank(chatId));
    }

    public void timeSettings(long chatId) {
        UserServices.createUserSettings(chatId);

        messageBuilder.createMessage(chatId, Constants.TIME, Buttons.time(chatId));
    }





    public void setTwoNumbers(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("2");

        messageBuilder.editMessage(Constants.NUMBER, chatId, messageId, Buttons.number(chatId));
    }

    public void setThreeNumbers(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("3");

        messageBuilder.editMessage(Constants.NUMBER, chatId, messageId, Buttons.number(chatId));
    }

    public void setFourNumbers(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("4");

        messageBuilder.editMessage(Constants.NUMBER, chatId, messageId, Buttons.number(chatId));
    }

    public void setUSD(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setCurrency("USD");

        messageBuilder.editMessage(Constants.CURRENCY, chatId, messageId, Buttons.currency(chatId));
    }

    public void setEUR(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setCurrency("EUR");

        messageBuilder.editMessage(Constants.CURRENCY, chatId, messageId, Buttons.currency(chatId));
    }

    public void setAllCurrency(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setCurrency("Всі валюти");

        messageBuilder.editMessage(Constants.CURRENCY, chatId, messageId, Buttons.currency(chatId));
    }

    public void setMono(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("Моно");

        messageBuilder.editMessage(Constants.BANK, chatId, messageId, Buttons.bank(chatId));
    }

    public void setPrivat(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("Приват");

        messageBuilder.editMessage(Constants.BANK, chatId, messageId, Buttons.bank(chatId));
    }

    public void setNBU(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("НБУ");

        messageBuilder.editMessage(Constants.BANK, chatId, messageId, Buttons.bank(chatId));
    }

    public void setAllBank(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("Всі банки");

        messageBuilder.editMessage(Constants.BANK, chatId, messageId, Buttons.bank(chatId));
    }
}
