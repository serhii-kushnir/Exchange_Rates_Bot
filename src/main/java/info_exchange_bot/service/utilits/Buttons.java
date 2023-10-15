package info_exchange_bot.service.utilits;

import static info_exchange_bot.service.utilits.Constants.BACK;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static info_exchange_bot.service.ui.UserServices.getUserSettingsById;

public final class Buttons {

    private Buttons() {
    }

    public static InlineKeyboardMarkup time(final long chatId) {
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
                        getUserSettingsById(chatId).getTime().equals("Вимкнути сповіщення")
                                ? "✅ Вимкнути сповіщення" : "Вимкнути сповіщення",
                        BACK
                });
    }

    public static InlineKeyboardMarkup bank(final long chatId) {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        getUserSettingsById(chatId).getBank().equals("НБУ") ? "✅ НБУ" : "НБУ",
                        getUserSettingsById(chatId).getBank().equals("Приват") ? "✅ Приват" : "Приват",
                        getUserSettingsById(chatId).getBank().equals("Моно") ? "✅ Моно" : "Моно",
                        getUserSettingsById(chatId).getBank().equals("Всі банки") ? "✅ Всі банки" : "Всі банки",
                        BACK
                });
    }

    public static InlineKeyboardMarkup currency(final long chatId) {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        getUserSettingsById(chatId).getCurrency().equals("USD") ? "✅ USD" : "USD",
                        getUserSettingsById(chatId).getCurrency().equals("EUR") ? "✅ EUR" : "EUR",
                        getUserSettingsById(chatId).getCurrency().equals("Всі валюти") ? "✅ Всі валюти" : "Всі валюти",
                        BACK
                });
    }

    public static InlineKeyboardMarkup number(final long chatId) {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        getUserSettingsById(chatId).getNumber().equals("2") ? "✅ 2" : "2",
                        getUserSettingsById(chatId).getNumber().equals("3") ? "✅ 3" : "3",
                        getUserSettingsById(chatId).getNumber().equals("4") ? "✅ 4" : "4",
                        BACK
                });
    }

    public static InlineKeyboardMarkup setting() {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        "\uD83C\uDFE6 Банк", "\uD83D\uDCB5 Валюта",
                        "\uD83D\uDD22 Кількість знаків після коми",
                        "\uD83C\uDFE0 На головну"
                });
    }

    public static InlineKeyboardMarkup info() {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"⚙ Налаштування"});
    }

    public static InlineKeyboardMarkup start() {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"\uD83D\uDCB1 Отримати курси валют", "⚙ Налаштування"});
    }
}
