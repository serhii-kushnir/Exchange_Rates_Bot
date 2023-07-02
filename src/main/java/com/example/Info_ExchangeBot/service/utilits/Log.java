package com.example.Info_ExchangeBot.service.utilits;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;

public class Log {

    public static void Error(TelegramApiException e) {
        System.out.println("LOG.ERROR: " + new Timestamp(System.currentTimeMillis()) +
                "\nError occurred: " + e.getMessage() + "\n");

    }

    public static void Info(String name) {
        System.out.println("\nLOG.INFO: " + new Timestamp(System.currentTimeMillis()) +
                "\nReply to using: " + name + "\n");
    }
}
