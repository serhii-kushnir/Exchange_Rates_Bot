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
        this.bank = "Моно";
        this.number = "2";
        //this.time = "Вимкнути сповіщення";
    }
}
