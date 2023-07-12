package com.example.Info_ExchangeBot.service.user;

import lombok.Data;

@Data
public class UserSettings {

    private String bank;
    private String currency;
    private String time;
    private String number;

    public UserSettings() {
        this.currency = "USD";
        this.bank = "Приват";
        this.number = "2";
    }
}
