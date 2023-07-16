package com.example.Info_ExchangeBot.service.utilits;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Log {

   private static final File FILE = new File("log.txt");

    public static void Error(TelegramApiException e) {
        System.out.println("\nLOG.ERROR: " + new Timestamp(System.currentTimeMillis()) +
                "\nError occurred: " + e.getMessage() + "\n");
    }

    public static void Info(String name, String text) {
        System.out.println("\nLOG.INFO: " + new Timestamp(System.currentTimeMillis()) +
                "\nReply to username: " + name + "\n" +
                "using text: " + text + "\n");
    }

    public static void button(String name, String callbackData) {
        String logText = "\nLOG.INFO: " + new Timestamp(System.currentTimeMillis()) +
                "\nReply to username: " + name +  "\n" +
                "using button: " + callbackData +  "\n";

        System.out.println(logText);

        try (FileWriter writer = new FileWriter(FILE, true))
        {
            writer.write(logText);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
