package info_exchange_bot.service.ui;

import lombok.Data;

@Data
public class UserModel {

    private String bank;
    private String currency;
    private String time;
    private String number;
    private String USD = "USD";
    private String EUR = "EUR";

    public UserModel() {
        this.currency = "Всі валюти";
        this.bank = "Всі банки";
        this.number = "2";
        this.time = "Вимкнути сповіщення";
    }
}
