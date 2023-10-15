package info_exchange_bot.banks.monobank;

import lombok.Data;

@Data
public final class CurrencyModelMonoBank {
    private final int currencyCodeA;
    private final int currencyCodeB;
    private final int date;
    private final float rateBuy;
    private final float rateCross;
    private final float rateSell;

}
