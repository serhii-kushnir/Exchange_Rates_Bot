package com.example.Info_ExchangeBot.service.utilits;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import static com.example.Info_ExchangeBot.service.utilits.ui.UserServices.getUserSettingsById;

public class Buttons {

    public static InlineKeyboardMarkup time(long chatId) {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        getUserSettingsById(chatId).getTime().equals("09:00") ? "✅ 09:00" : "09:00",
                        getUserSettingsById(chatId).getTime().equals("10:00") ? "✅ 10:00" : "10:00",
                        getUserSettingsById(chatId).getTime().equals("11:00") ? "✅ 11:00" : "11:00",
                        getUserSettingsById(chatId).getTime().equals("12:00") ? "✅ 12:00" : "12:00",
                        getUserSettingsById(chatId).getTime().equals("13:00") ? "✅ 13:00" : "13:00",
                        getUserSettingsById(chatId).getTime().equals("14:00") ? "✅ 14:00" : "14:00",
                        getUserSettingsById(chatId).getTime().equals("15:00") ? "✅ 15:00" : "15:00",
                        getUserSettingsById(chatId).getTime().equals("16:00") ? "✅ 16:00" : "16:00",
                        getUserSettingsById(chatId).getTime().equals("17:00") ? "✅ 17:00" : "17:00",
                        getUserSettingsById(chatId).getTime().equals("18:00") ? "✅ 18:00" : "18:00",
                        getUserSettingsById(chatId).getTime().equals("Вимкнути сповіщення") ?
                                "✅ Вимкнути сповіщення" : "Вимкнути сповіщення",
                        "\uD83D\uDD19 Назад"
                });
    }

    public static InlineKeyboardMarkup bank(long chatId) {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        getUserSettingsById(chatId).getBank().equals("НБУ") ? "✅ НБУ" : "НБУ",
                        getUserSettingsById(chatId).getBank().equals("Приват") ? "✅ Приват" : "Приват",
                        getUserSettingsById(chatId).getBank().equals("Моно") ? "✅ Моно" : "Моно",
                        getUserSettingsById(chatId).getBank().equals("Всі банки") ? "✅ Всі банки" : "Всі банки",
                        "\uD83D\uDD19 Назад"
                });
    }

    public static InlineKeyboardMarkup currency(long chatId) {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        getUserSettingsById(chatId).getCurrency().equals("USD") ? "✅ USD" : "USD",
                        getUserSettingsById(chatId).getCurrency().equals("EUR") ? "✅ EUR" : "EUR",
                        "\uD83D\uDD19 Назад"
                });
    }

    public static InlineKeyboardMarkup number(long chatId) {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        getUserSettingsById(chatId).getNumber().equals("2") ? "✅ 2" : "2",
                        getUserSettingsById(chatId).getNumber().equals("3") ? "✅ 3" : "3",
                        getUserSettingsById(chatId).getNumber().equals("4") ? "✅ 4" : "4",
                        "\uD83D\uDD19 Назад"
                });
    }

    public static InlineKeyboardMarkup setting() {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        "\uD83C\uDFE6 Банк", "\uD83D\uDCB5 Валюта", "\uD83D\uDD52 Час сповіщень",
                        "\uD83D\uDD22 Кількість знаків після коми", "\uD83C\uDFE0 На головну"
                });
    }

    public static InlineKeyboardMarkup info() {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"⚙ Налаштування"});
    }

    public static InlineKeyboardMarkup start() {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"\uD83D\uDCB1 Отримати інфо", "⚙ Налаштування"});
    }
}
