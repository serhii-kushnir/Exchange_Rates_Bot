package info_exchange_bot.service.utilits;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.sql.Timestamp;

import java.time.LocalDate;

import static info_exchange_bot.service.utilits.Constants.ERROR_FILED;

public class Log {

    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final String DIRECTORY_PATH = "log";
    private static final String FILE_PATH = DIRECTORY_PATH + File.separator + CURRENT_DATE + ".txt";

    private Log() {
    }

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

    public static void error(TelegramApiException e) {
        String logText ="\nLOG.ERROR: " + new Timestamp(System.currentTimeMillis()) +
                "\nError occurred: " + e.getMessage() + "\n";

        System.out.println(logText);

        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(logText);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ERROR_FILED + ex.getMessage());
        }
    }

    public static void info(String name, String text) {
        String logText = "\nLOG.INFO: " + new Timestamp(System.currentTimeMillis()) +
                "\nReply to username: " + name + "\n" +
                "using text: " + text + "\n";

        System.out.println(logText);

        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(logText);
            writer.flush();
        } catch (IOException e) {
            System.out.println(ERROR_FILED + e.getMessage());
        }
    }

    public static void button(String name, String callbackData) {
        String logText = "\nLOG.INFO: " + new Timestamp(System.currentTimeMillis()) +
                "\nReply to username: " + name +  "\n" +
                "using button: " + callbackData +  "\n";

        System.out.println(logText);

        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(logText);
            writer.flush();
        } catch (IOException e) {
            System.out.println(ERROR_FILED + e.getMessage());
        }
    }
}