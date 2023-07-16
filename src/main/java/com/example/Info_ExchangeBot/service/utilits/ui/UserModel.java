package com.example.Info_ExchangeBot.service.utilits.ui;

import lombok.Data;

@Data
public class UserModel {

    private String bank;
    private String currency;
    private String time;
    private String number;
    private String USD;
    private String EUR;

    public UserModel() {
        this.currency = "USD";
        this.bank = "Приват";
        this.number = "2";
        //this.time = "Вимкнути сповіщення";
    }
}
