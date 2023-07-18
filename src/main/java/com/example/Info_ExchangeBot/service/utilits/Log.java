package com.example.Info_ExchangeBot.service.utilits;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Log {

    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final String DIRECTORY_PATH = "log";
    private static final String FILE_PATH = DIRECTORY_PATH + File.separator + CURRENT_DATE + ".txt";

    static {
        createDirectory();
    }

    private static void createDirectory() {
        Path directoryPath = Paths.get(DIRECTORY_PATH);
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                System.out.println("Failed to create log directory: " + e.getMessage());
            }
        }
    }

    private static void createLogFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Failed to create log file: " + e.getMessage());
            }
        }
    }

    public static void Error(TelegramApiException e) {
        createLogFile();

        String logText ="\nLOG.ERROR: " + new Timestamp(System.currentTimeMillis()) +
                "\nError occurred: " + e.getMessage() + "\n";

        System.out.println(logText);

        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(logText);
            writer.flush();
        } catch (IOException ex) {
            System.out.println("Failed to write to log file: " + ex.getMessage());
        }
    }

    public static void Info(String name, String text) {
        createLogFile();

        String logText = "\nLOG.INFO: " + new Timestamp(System.currentTimeMillis()) +
                "\nReply to username: " + name + "\n" +
                "using text: " + text + "\n";

        System.out.println(logText);

        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(logText);
            writer.flush();
        } catch (IOException e) {
            System.out.println("Failed to write to log file: " + e.getMessage());
        }
    }

    public static void button(String name, String callbackData) {
        createLogFile();

        String logText = "\nLOG.INFO: " + new Timestamp(System.currentTimeMillis()) +
                "\nReply to username: " + name +  "\n" +
                "using button: " + callbackData +  "\n";

        System.out.println(logText);

        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(logText);
            writer.flush();
        } catch (IOException e) {
            System.out.println("Failed to write to log file: " + e.getMessage());
        }
    }
}