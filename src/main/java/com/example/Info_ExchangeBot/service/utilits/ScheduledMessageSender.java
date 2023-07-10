package com.example.Info_ExchangeBot.service.utilits;

import com.example.Info_ExchangeBot.banks.nbu.CurrencyServiceNBU;
import com.example.Info_ExchangeBot.banks.privatbank.CurrencyServicePrivatBank;
import com.example.Info_ExchangeBot.service.TelegramBot;
import com.example.Info_ExchangeBot.service.utilits.commands.BotCommandsHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduledMessageSender {

    BotCommandsHandler botCommandsHandler;
    MessageBuilder messageBuilder;

    public ScheduledMessageSender(TelegramBot telegramBot) {
        messageBuilder = new MessageBuilder(telegramBot);
        botCommandsHandler = new BotCommandsHandler(telegramBot);
    }

    public void scheduleMessage(long chatId,  LocalDateTime scheduledTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        long delayMillis = calculateDelayMillis(currentTime, scheduledTime);

        if (delayMillis < 0) {
            delayMillis = 0;
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("plan");
                messageBuilder.createMessage(chatId, sendMessage());
            }
        }, delayMillis);
    }

    private long calculateDelayMillis(LocalDateTime currentTime, LocalDateTime scheduledTime) {
        return java.time.Duration.between(currentTime, scheduledTime).toMillis();
    }

    private String sendMessage() {
        LocalDate currentDate = LocalDate.now();
        StringBuilder answer = new StringBuilder();
        answer.append("TEST");
        answer.append(currentDate);
        answer.append("\n\n");
        answer.append(CurrencyServicePrivatBank.getCurrencyInformation("USD", "/number"));
        answer.append(CurrencyServiceNBU.getCurrencyInformation("USD"));
        return answer.toString();
    }
}