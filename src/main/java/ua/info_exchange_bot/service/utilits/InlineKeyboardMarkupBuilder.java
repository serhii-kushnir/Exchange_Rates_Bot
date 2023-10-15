package ua.info_exchange_bot.service.utilits;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public final class InlineKeyboardMarkupBuilder {

    private InlineKeyboardMarkupBuilder() {
    }

    public static InlineKeyboardMarkup buildMarkup(final String[] buttonTexts) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonText : buttonTexts) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonText);
            button.setCallbackData(buttonText);
            keyboard.add(List.of(button));
        }

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboard);
        return markup;
    }
}
