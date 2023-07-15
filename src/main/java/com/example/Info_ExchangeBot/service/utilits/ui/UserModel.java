package com.example.Info_ExchangeBot.service.utilits.ui;

import lombok.Data;

@Data
public class UserModel {

    private String bank;
    private String currency;
    private String time;
    private String number;

    public UserModel() {
        this.currency = "USD";
        this.bank = "Всі банки";
        this.time = "Вимкнути сповіщення";
        this.number = "2";
    }
}
