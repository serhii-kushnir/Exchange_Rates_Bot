package ua.info_exchange_bot.service.utilits.commands;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.Arrays;
import java.util.List;

public final class BotCommandListMenu {

    private BotCommandListMenu() {
    }

    public static List<BotCommand> getBotCommandList() {
        return Arrays.asList(
                new BotCommand("/home", "Повернутися на головну сторінку"),
                new BotCommand("/info", "Отримати курси валют"),
                new BotCommand("/setting", "Налаштуавння"),
                new BotCommand("/bank", "Налаштуавння банку"),
                new BotCommand("/currency", "Налаштуавння валюти"),
                new BotCommand("/number", "Налаштуавння знаків")
        );
    }
}
