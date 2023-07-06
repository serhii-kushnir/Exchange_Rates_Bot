package com.example.Info_ExchangeBot.banks.monobank;

import lombok.Data;

@Data
public class CurrencyModelMonoBank {
    private final int currencyCodeA;
    private final int currencyCodeB;
    private final int date;
    private final float rateBuy;
    private final float rateCross;
    private final float rateSell ;
}