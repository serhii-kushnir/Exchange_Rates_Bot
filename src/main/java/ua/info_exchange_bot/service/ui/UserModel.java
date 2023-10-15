package ua.info_exchange_bot.service.ui;

import lombok.Data;

@Data
public class UserModel {
    private String bank;
    private String currency;
    private String time;
    private String number;
    private String usd = "USD";
    private String eur = "EUR";

    public UserModel() {
        this.currency = "Всі валюти";
        this.bank = "Всі банки";
        this.number = "2";
        this.time = "Вимкнути сповіщення";
    }
}
