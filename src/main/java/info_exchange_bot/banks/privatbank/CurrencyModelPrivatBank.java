package info_exchange_bot.banks.privatbank;

import lombok.Data;

@Data
public class CurrencyModelPrivatBank {
    private final String ccy;
    private final String baseCcy;
    private final String buy;
    private final String sale;
}