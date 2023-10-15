package ua.info_exchange_bot.service;

import ua.info_exchange_bot.service.utilits.Log;
import ua.info_exchange_bot.service.utilits.commands.BotCommands;

import static ua.info_exchange_bot.service.ui.UserServices.createUserSettings;

public final class ProcessHandler {

    private final BotCommands botCommands;

    public ProcessHandler(final TelegramBot telegramBot) {
        this.botCommands = new BotCommands(telegramBot);
    }

    public void message(final String messageText, final String username, final long chatId) {
        createUserSettings(chatId);

        switch (messageText) {
            case "/start" -> botCommands.start(chatId);
            case "/home" -> botCommands.home(chatId);
            case "/info" -> botCommands.infoMessage(chatId);
            case "/setting" -> botCommands.settingsMessage(chatId);
            case "/bank" -> botCommands.bankSettings(chatId);
            case "/currency" -> botCommands.currencySettings(chatId);
            case "/time" -> botCommands.timeSettings(chatId);
            case "/number" -> botCommands.numberSettings(chatId);
            default -> throw new IllegalStateException("Unexpected value: " + messageText);
        }

        Log.info(username, messageText);
    }

    public void callbackQuery(final String callbackData, final String userName, final long chatIdBackQuery, final long messageId) {
        createUserSettings(chatIdBackQuery);

        switch (callbackData) {
            case "\uD83D\uDCB1 Отримати курси валют" -> botCommands.infoMessage(chatIdBackQuery);
            case "⚙ Налаштування", "\uD83D\uDD19 Назад" -> botCommands.settingsMessage(chatIdBackQuery);
            case "\uD83D\uDD22 Кількість знаків після коми" -> botCommands.numberSettings(chatIdBackQuery);
            case "\uD83D\uDCB5 Валюта" -> botCommands.currencySettings(chatIdBackQuery);
            case "\uD83C\uDFE6 Банк" -> botCommands.bankSettings(chatIdBackQuery);
            case "\uD83D\uDD52 Час сповіщення" -> botCommands.timeSettings(chatIdBackQuery);
            case "Приват" -> botCommands.setPrivat(chatIdBackQuery, messageId);
            case "Моно" -> botCommands.setMono(chatIdBackQuery, messageId);
            case "НБУ" -> botCommands.setNBU(chatIdBackQuery, messageId);
            case "Всі валюти" -> botCommands.setAllCurrency(chatIdBackQuery, messageId);
            case "Всі банки" -> botCommands.setAllBank(chatIdBackQuery, messageId);
            case "USD" -> botCommands.setUSD(chatIdBackQuery, messageId);
            case "EUR" -> botCommands.setEUR(chatIdBackQuery, messageId);
            case "2" -> botCommands.setTwoNumbers(chatIdBackQuery, messageId);
            case "3" -> botCommands.setThreeNumbers(chatIdBackQuery, messageId);
            case "4" -> botCommands.setFourNumbers(chatIdBackQuery, messageId);
            case "\uD83C\uDFE0 На головну" -> botCommands.home(chatIdBackQuery);
            default -> throw new IllegalStateException("Unexpected value: " + callbackData);
        }

        Log.button(userName, callbackData);
    }
}
